package com.iotiq.application.usecase;

import com.iotiq.api.dto.CreateMessageRequestDTO;
import com.iotiq.api.dto.CreateMessageResponseDTO;

public interface CreateMessageUseCase {
    CreateMessageResponseDTO create(CreateMessageRequestDTO request);
}
