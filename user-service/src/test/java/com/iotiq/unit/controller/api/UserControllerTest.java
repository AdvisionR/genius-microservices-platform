package com.iotiq.unit.controller.api;

import com.iotiq.api.controller.UserController;
import com.iotiq.api.dto.ApiResponse;
import com.iotiq.api.dto.CreateUserRequestDTO;
import com.iotiq.application.usecase.GetUserUseCase;
import com.iotiq.application.usecase.UserCreateUseCase;
import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.infrastructure.security.CurrentUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static com.iotiq.message.ResponseMessages.FETCH_USER;
import static com.iotiq.message.ResponseMessages.USER_REGISTERED_SUCCESSFULLY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserCreateUseCase userCreateUseCase;

    @Mock
    private GetUserUseCase getUserUseCase;

    @Mock
    private CurrentUser currentUser;

    @InjectMocks
    private UserController userController;

    @Test
    void createUser_shouldReturnCreatedResponse_whenSuccess() {
        // Arrange
        CreateUserRequestDTO requestDTO = CreateUserRequestDTO.builder()
                .authUuid(UUID.randomUUID())
                .userName("testUser")
                .email("test@example.com")
                .build();


        UserProfileResponseDTO responseDTO = UserProfileResponseDTO.builder()
                .uuid(UUID.randomUUID())
                .userName("testUser")
                .email("test@example.com")
                .avatar("avatar.png")
                .build();

        when(userCreateUseCase.createUser(requestDTO)).thenReturn(responseDTO);

        // Act
        ResponseEntity<ApiResponse<UserProfileResponseDTO>> response = userController.createUser(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(USER_REGISTERED_SUCCESSFULLY, response.getBody().message());
        assertEquals(responseDTO, response.getBody().data());
    }

    @Test
    void getUser_shouldReturnUserProfileResponse_whenUserExists() {
        // Arrange
        String userName = "currentUser";
        UserProfileResponseDTO responseDTO = UserProfileResponseDTO.builder()
                .uuid(UUID.randomUUID())
                .userName(userName)
                .email("current@example.com")
                .avatar("avatar.png")
                .build();

        when(currentUser.getUserName()).thenReturn(userName);
        when(getUserUseCase.getUserByUserName(userName)).thenReturn(responseDTO);

        // Act
        ResponseEntity<ApiResponse<UserProfileResponseDTO>> response = userController.getUser();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(FETCH_USER, response.getBody().message());
        assertEquals(responseDTO, response.getBody().data());
    }
}
