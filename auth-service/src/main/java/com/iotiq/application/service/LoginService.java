package com.iotiq.application.service;

import com.iotiq.api.dto.LoginRequestDTO;
import com.iotiq.api.dto.LoginResponseDTO;
import com.iotiq.application.usecase.LoginUseCase;
import com.iotiq.domain.model.Auth;
import com.iotiq.domain.repository.AuthRepository;
import com.iotiq.exception.InvalidCredentialsException;
import com.iotiq.exception.UserNotFoundException;
import com.iotiq.infrastructure.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        Auth auth = authRepository.findByUserName(request.userName())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(request.password(), auth.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtService.generateAccessToken(
                auth.getUserName(),
                Map.of("email", auth.getEmail())
        );
        String refreshToken = jwtService.generateRefreshToken(auth.getUserName());

        return new LoginResponseDTO(accessToken, refreshToken);
    }
}
