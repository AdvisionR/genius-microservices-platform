package com.iotiq.application.service;

import com.iotiq.api.dto.CreateMessageRequestDTO;
import com.iotiq.api.dto.CreateMessageResponseDTO;
import com.iotiq.application.usecase.CreateMessageUseCase;
import com.iotiq.domain.model.Chat;
import com.iotiq.domain.model.Message;
import com.iotiq.domain.repository.ChatRepository;
import com.iotiq.domain.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMessageService implements CreateMessageUseCase {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    @Override
    public CreateMessageResponseDTO create(CreateMessageRequestDTO request) {
        Chat chat = chatRepository.findByUuid(request.getChatUuid());

        Message message = Message.builder()
                .sender(request.getSender())
                .content(request.getContent())
                .chat(chat)
                .build();

        Message savedMessage = messageRepository.save(message);

        return CreateMessageResponseDTO.builder()
                .uuid(savedMessage.getUuid())
                .chatUuid(chat.getUuid())
                .content(savedMessage.getContent())
                .sender(savedMessage.getSender())
                .createdAt(savedMessage.getCreatedAt())
                .build();
    }
}
