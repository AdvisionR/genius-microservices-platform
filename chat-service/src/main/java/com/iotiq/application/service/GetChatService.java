package com.iotiq.application.service;

import com.iotiq.api.dto.ChatDTO;
import com.iotiq.api.dto.ChatDetailDTO;
import com.iotiq.api.dto.MessageDTO;
import com.iotiq.application.usecase.GetChatUseCase;
import com.iotiq.domain.model.Chat;
import com.iotiq.domain.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetChatService implements GetChatUseCase {
    private final ChatRepository chatRepository;

    @Override
    public List<ChatDTO> getAll(UUID userUuid) {
        return chatRepository.findAllByUserUuid(userUuid).stream()
                .map(chat -> ChatDTO.builder()
                        .uuid(chat.getUuid())
                        .title(chat.getTitle())
                        .createdAt(chat.getCreatedAt())
                        .build())
                .toList();
    }

    @Override
    public ChatDetailDTO get(UUID chatUuid) {
        Chat chat = chatRepository.findByUuid(chatUuid);

        List<MessageDTO> messageDTOs = chat.getMessages().stream()
                .map(message -> MessageDTO.builder()
                        .uuid(message.getUuid())
                        .sender(message.getSender())
                        .content(message.getContent())
                        .createdAt(message.getCreatedAt())
                        .build())
                .toList();

        return ChatDetailDTO.builder()
                .uuid(chat.getUuid())
                .title(chat.getTitle())
                .createdAt(chat.getCreatedAt())
                .messages(messageDTOs)
                .build();
    }
}
