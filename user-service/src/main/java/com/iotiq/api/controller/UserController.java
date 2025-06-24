package com.iotiq.api.controller;

import com.iotiq.api.dto.ApiResponse;
import com.iotiq.api.dto.CreateUserRequestDTO;
import com.iotiq.application.usecase.GetUserUseCase;
import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.application.usecase.UserCreateUseCase;
import com.iotiq.infrastructure.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.iotiq.infrastructure.config.RestApis.*;
import static com.iotiq.message.ResponseMessages.FETCH_USER;
import static com.iotiq.message.ResponseMessages.USER_REGISTERED_SUCCESSFULLY;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_PROFILE)
public class UserController {
    private final UserCreateUseCase userCreateUseCase;
    private final GetUserUseCase getUserUseCase;
    private final CurrentUser currentUser;

    @PostMapping(CREATE_USER)
    public ResponseEntity<ApiResponse<UserProfileResponseDTO>> createUser(@RequestBody CreateUserRequestDTO dto) {
        UserProfileResponseDTO response = userCreateUseCase.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(USER_REGISTERED_SUCCESSFULLY, response));
    }

    @GetMapping(GET_USER)
    public ResponseEntity<ApiResponse<UserProfileResponseDTO>> getUser() {
        UserProfileResponseDTO response = getUserUseCase.getUserByUserName(currentUser.getUserName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(FETCH_USER, response));
    }
}
