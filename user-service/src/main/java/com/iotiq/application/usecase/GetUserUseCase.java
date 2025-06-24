package com.iotiq.application.usecase;

import com.iotiq.dto.UserProfileResponseDTO;

public interface GetUserUseCase {
    UserProfileResponseDTO getUserByUserName(String userName);
}
