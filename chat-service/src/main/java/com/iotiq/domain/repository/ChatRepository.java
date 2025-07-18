package com.iotiq.domain.repository;

import com.iotiq.api.dto.ChatDetailDTO;
import com.iotiq.domain.model.Chat;

import java.util.List;
import java.util.UUID;

public interface ChatRepository {
    Chat save(Chat chat);
    List<Chat> findAllByUserUuid(UUID userUuid);
    Chat findByUuid(UUID chatUuid);
}
