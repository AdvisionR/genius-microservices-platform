package com.iotiq.api.controller;

import com.iotiq.api.dto.ApiResponse;
import com.iotiq.api.dto.CreateUserRequestDTO;
import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.application.usecase.UserCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.iotiq.infrastructure.config.RestApis.CREATE_USER;
import static com.iotiq.infrastructure.config.RestApis.USER_PROFILE;
import static com.iotiq.message.ResponseMessages.USER_REGISTERED_SUCCESSFULLY;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_PROFILE)
public class UserController {
    private final UserCreateUseCase userCreateUseCase;

    @PostMapping(CREATE_USER)
    public ResponseEntity<ApiResponse<UserProfileResponseDTO>> createUser(@RequestBody CreateUserRequestDTO dto) {
        UserProfileResponseDTO response = userCreateUseCase.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(USER_REGISTERED_SUCCESSFULLY, response));
    }
}
