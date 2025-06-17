package com.iotiq.api.controller;

import com.iotiq.api.dto.ApiResponse;
import com.iotiq.api.dto.RegisterRequestDTO;
import com.iotiq.api.dto.RegisterResponseDTO;
import com.iotiq.application.usecase.RegisterUseCase;
import com.iotiq.domain.exception.PasswordNotMatchException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.iotiq.infrastructure.config.RestApis.AUTH;
import static com.iotiq.infrastructure.config.RestApis.REGISTER;
import static com.iotiq.message.ResponseMessages.USER_REGISTERED_SUCCESSFULLY;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final RegisterUseCase registerUseCase;

    @PostMapping(REGISTER)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<RegisterResponseDTO>> register(@RequestBody @Valid RegisterRequestDTO request) {
        RegisterResponseDTO response = registerUseCase.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(USER_REGISTERED_SUCCESSFULLY, response));
    }
}
