package com.iotiq.infrastructure.persistence;

import com.iotiq.api.dto.ChatDetailDTO;
import com.iotiq.domain.model.Chat;
import com.iotiq.domain.repository.ChatRepository;
import com.iotiq.exception.ChatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepository {
    private final JpaChatRepository jpaChatRepository;

    @Override
    public Chat save(Chat chat) {
        return jpaChatRepository.save(chat);
    }

    @Override
    public List<Chat> findAllByUserUuid(UUID userUuid) {
        return jpaChatRepository.findAllByUserUuidOrderByCreatedAtDesc(userUuid);
    }

    @Override
    public Chat findByUuid(UUID chatUuid) {
        return jpaChatRepository.findById(chatUuid)
                .orElseThrow(ChatNotFoundException::new);
    }
}
