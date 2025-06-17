package com.iotiq.api.controller;

import com.iotiq.api.dto.*;
import com.iotiq.api.message.ResponseMessages;
import com.iotiq.application.usecase.LoginUseCase;
import com.iotiq.application.usecase.RegisterUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.iotiq.infrastructure.config.RestApis.*;
import static com.iotiq.message.ResponseMessages.USER_REGISTERED_SUCCESSFULLY;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping(REGISTER)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<RegisterResponseDTO>> register(@RequestBody @Valid RegisterRequestDTO request) {
        RegisterResponseDTO response = registerUseCase.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(USER_REGISTERED_SUCCESSFULLY, response));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = loginUseCase.login(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseMessages.LOGIN_SUCCESSFUL.getMessage(), response));
    }
}
