package com.iotiq.integration.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotiq.api.dto.ApiResponse;
import com.iotiq.api.dto.RegisterRequestDTO;
import com.iotiq.domain.repository.AuthRepository;
import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.infrastructure.client.UserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserClient userClient;

    @BeforeEach
    void setUp() {
        UserProfileResponseDTO userProfile = new UserProfileResponseDTO(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "testuser",
                "test@example.com",
                "Test User",
                "+905551112233",
                "https://example.com/avatar.png"
        );

        ApiResponse<UserProfileResponseDTO> apiResponse = new ApiResponse<>(
                "User created",
                userProfile
        );

        when(userClient.createUser(any())).thenReturn(
                ResponseEntity.ok(apiResponse)
        );
    }

    @Test
    @Transactional
    void register_shouldReturnCreated_whenValidRequest() throws Exception {
        RegisterRequestDTO request = new RegisterRequestDTO(
                "testuser",
                "Password123!",
                "Password123!",
                "test@example.com"
        );

        mockMvc.perform(post("/dev/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User registered successfully"))
                .andExpect(jsonPath("$.data.userName").value("testuser"))
                .andExpect(jsonPath("$.data.email").value("test@example.com"));

        assertThat(authRepository.findByUserName("testuser")).isPresent();
    }
}
