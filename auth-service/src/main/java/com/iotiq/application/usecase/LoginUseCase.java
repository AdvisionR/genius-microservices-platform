package com.iotiq.application.usecase;

import com.iotiq.api.dto.LoginRequestDTO;
import com.iotiq.api.dto.LoginResponseDTO;

public interface LoginUseCase {
    LoginResponseDTO login(LoginRequestDTO request);
}
