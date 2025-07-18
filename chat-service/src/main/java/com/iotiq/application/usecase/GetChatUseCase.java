package com.iotiq.application.usecase;

import com.iotiq.api.dto.ChatDTO;
import com.iotiq.api.dto.ChatDetailDTO;

import java.util.List;
import java.util.UUID;

public interface GetChatUseCase {
    List<ChatDTO> getAll(UUID userUuid);
    ChatDetailDTO get(UUID chatUuid);
}
