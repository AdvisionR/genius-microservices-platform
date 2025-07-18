package com.iotiq.application.service;

import com.iotiq.api.dto.ApiResponse;
import com.iotiq.api.dto.CreateChatRequestDTO;
import com.iotiq.api.dto.CreateChatResponseDTO;
import com.iotiq.application.usecase.CreateChatUseCase;
import com.iotiq.domain.model.Chat;
import com.iotiq.domain.repository.ChatRepository;
import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.enums.ServiceName;
import com.iotiq.exception.ServiceUnavailableException;
import com.iotiq.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateChatService implements CreateChatUseCase {
    private final ChatRepository chatRepository;
    private final UserClient userClient;

    @Override
    public CreateChatResponseDTO create(CreateChatRequestDTO request) {
        ResponseEntity<ApiResponse<UserProfileResponseDTO>> response = userClient.getUser();

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new ServiceUnavailableException(ServiceName.USER);
        }

        Chat chat = Chat.builder()
                .title(request.getTitle())
                .userUuid(request.getUserUuid())
                .build();

        Chat savedChat = chatRepository.save(chat);

        return CreateChatResponseDTO.builder()
                .uuid(savedChat.getUuid())
                .title(savedChat.getTitle())
                .userUuid(savedChat.getUserUuid())
                .createdAt(savedChat.getCreatedAt())
                .messages(savedChat.getMessages())
                .build();
    }
}
