package com.iotiq.application.usecase;

import com.iotiq.api.dto.CreateChatRequestDTO;
import com.iotiq.api.dto.CreateChatResponseDTO;

public interface CreateChatUseCase {
    CreateChatResponseDTO create(CreateChatRequestDTO request);
}
