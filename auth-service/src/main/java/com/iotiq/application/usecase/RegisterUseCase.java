package com.iotiq.application.usecase;

import com.iotiq.api.dto.RegisterRequestDTO;
import com.iotiq.api.dto.RegisterResponseDTO;

public interface RegisterUseCase {
    RegisterResponseDTO register(RegisterRequestDTO request);
}
