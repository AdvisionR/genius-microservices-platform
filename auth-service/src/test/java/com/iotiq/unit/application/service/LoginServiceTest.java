package com.iotiq.unit.application.service;

import com.iotiq.api.dto.LoginRequestDTO;
import com.iotiq.api.dto.LoginResponseDTO;
import com.iotiq.application.service.LoginService;
import com.iotiq.domain.model.Auth;
import com.iotiq.domain.repository.AuthRepository;
import com.iotiq.enums.UserRole;
import com.iotiq.exception.InvalidCredentialsException;
import com.iotiq.exception.UserNotFoundException;
import com.iotiq.security.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @Mock
    private AuthRepository authRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private LoginService loginService;

    @Test
    void login_shouldReturnTokens_whenCredentialsAreValid() {
        LoginRequestDTO request = new LoginRequestDTO("testUser", "correctPassword");

        Auth auth = Auth.builder()
                .uuid(UUID.randomUUID())
                .userName("testUser")
                .email("test@example.com")
                .password("hashedPassword")
                .role(UserRole.USER)
                .build();

        when(authRepository.findByUserName("testUser")).thenReturn(Optional.of(auth));
        when(passwordEncoder.matches(
                "correctPassword",
                "hashedPassword")).thenReturn(true);
        when(jwtService.generateAccessToken(eq("testUser"), anyMap())).thenReturn("access-token");
        when(jwtService.generateRefreshToken("testUser")).thenReturn("refresh-token");

        LoginResponseDTO response = loginService.login(request);

        assertThat(response.accessToken()).isEqualTo("access-token");
        assertThat(response.refreshToken()).isEqualTo("refresh-token");
    }

    @Test
    void login_shouldThrowUserNotFoundException_whenUserNotFound() {
        when(authRepository.findByUserName("notfound")).thenReturn(Optional.empty());

        LoginRequestDTO request = new LoginRequestDTO("notfound", "any");

        assertThatThrownBy(() -> loginService.login(request))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void login_shouldThrowInvalidCredentialsException_whenPasswordIsIncorrect() {
        Auth auth = Auth.builder()
                .uuid(UUID.randomUUID())
                .userName("testUser")
                .email("test@example.com")
                .password("hashedPassword")
                .build();

        when(authRepository.findByUserName("testUser")).thenReturn(Optional.of(auth));
        when(passwordEncoder.matches("wrongPassword", "hashedPassword"))
                .thenReturn(false);

        LoginRequestDTO request = new LoginRequestDTO("testUser", "wrongPassword");

        assertThatThrownBy(() -> loginService.login(request))
                .isInstanceOf(InvalidCredentialsException.class);
    }
}
