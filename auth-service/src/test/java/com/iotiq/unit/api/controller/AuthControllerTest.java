package com.iotiq.unit.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotiq.api.controller.AuthController;
import com.iotiq.api.dto.LoginRequestDTO;
import com.iotiq.api.dto.LoginResponseDTO;
import com.iotiq.api.dto.RegisterRequestDTO;
import com.iotiq.api.dto.RegisterResponseDTO;
import com.iotiq.application.usecase.LoginUseCase;
import com.iotiq.application.usecase.RegisterUseCase;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(TestSecurityConfig.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoginUseCase loginUseCase;

    @MockitoBean
    private RegisterUseCase registerUseCase;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void register_shouldReturnCreated_whenGivenValidInput() throws Exception {
        UUID uuid = UUID.randomUUID();

        RegisterRequestDTO request = new RegisterRequestDTO("john_doe","StrongPass123!","StrongPass123!","john@example.com");
        RegisterResponseDTO response = new RegisterResponseDTO(uuid, "john_doe", "john@example.com");

        when(registerUseCase.register(any())).thenReturn(response);

        mockMvc.perform(post("/dev/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User registered successfully"))
                .andExpect(jsonPath("$.data.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.data.userName").value("john_doe"))
                .andExpect(jsonPath("$.data.email").value("john@example.com"));
    }

    @Test
    void login_shouldReturnTokens_whenGivenValidInput() throws Exception {
        LoginRequestDTO request = new LoginRequestDTO("testUser", "Password123!");
        LoginResponseDTO response = new LoginResponseDTO("access-token", "refresh-token");

        when(loginUseCase.login(any())).thenReturn(response);

        mockMvc.perform(post("/dev/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successfully"))
                .andExpect(jsonPath("$.data.accessToken").value("access-token"))
                .andExpect(jsonPath("$.data.refreshToken").value("refresh-token"));
    }
}
