package com.iotiq.application.usecase;

import com.iotiq.api.dto.CreateUserRequestDTO;
import com.iotiq.dto.UserProfileResponseDTO;

public interface UserCreateUseCase {
    UserProfileResponseDTO createUser(CreateUserRequestDTO request);
}
