package com.iotiq.unit.infrastructure.security;

import com.iotiq.security.jwt.JwtProperties;
import com.iotiq.security.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class JwtServiceTest {
    private JwtService jwtService;
    private final String secretKey = "genius-microservices-platform-jwt-secret-key-256-bit";
    private final Long accessTokenExpirationMs = 1000 * 60 * 10L;
    private final Long refreshTokenExpirationMs = 1000 * 60 * 60 * 24 * 7L;

    @BeforeEach
    void setUp() {

        JwtProperties jwtProperties = new JwtProperties(secretKey, accessTokenExpirationMs, refreshTokenExpirationMs);
        jwtService = new JwtService(jwtProperties);
        jwtService.init();
    }

    @Test
    void generateAccessToken_shouldReturnAccessToken_whenGivenValidInput() {
        String subject = "test-user";
        Map<String, Object> claims = Map.of("role", "admin");

        String token = jwtService.generateAccessToken(subject, claims);

        assertThat(token).isNotNull();
        assertThat(jwtService.isTokenValid(token)).isTrue();
        assertThat(jwtService.extractSubject(token)).isEqualTo(subject);
    }

    @Test
    void generateRefreshToken_shouldReturnRefreshToken_whenGivenValidInput() {
        String subject = "refresh-user";
        String token = jwtService.generateRefreshToken(subject);

        assertThat(token).isNotBlank();
        assertThat(jwtService.isTokenValid(token)).isTrue();
        assertThat(jwtService.extractSubject(token)).isEqualTo(subject);
    }

    @Test
    void isTokenValid_shouldReturnFalse_whenGivenInvalidToken() {
        String invalidToken = "this-is-not-a-valid-token";

        boolean isValid = jwtService.isTokenValid(invalidToken);

        assertThat(isValid).isFalse();
    }

    @Test
    void isTokenValid_shouldThrowException_whenGivenExpiredToken() throws InterruptedException {
        JwtProperties jwtProperties = new JwtProperties(secretKey, 1L, refreshTokenExpirationMs);
        JwtService jwtService = new JwtService(jwtProperties);
        jwtService.init();

        String token = jwtService.generateAccessToken("expiring-user", Map.of());

        Thread.sleep(2000);

        boolean isValid = jwtService.isTokenValid(token);

        assertThat(isValid).isFalse();
    }
}
