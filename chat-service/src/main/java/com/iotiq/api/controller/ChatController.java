package com.iotiq.api.controller;

import com.iotiq.api.dto.*;
import com.iotiq.application.usecase.CreateChatUseCase;
import com.iotiq.application.usecase.GetChatUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.iotiq.infrastructure.config.RestApis.*;
import static com.iotiq.message.ResponseMessages.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CHAT)
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final CreateChatUseCase createChatUseCase;
    private final GetChatUseCase getChatUseCase;

    @PostMapping(CREATE)
    public ResponseEntity<ApiResponse<CreateChatResponseDTO>> create(@RequestBody CreateChatRequestDTO request) {
        CreateChatResponseDTO response = createChatUseCase.create(request);
        logger.info("New chat created: {}", request.getTitle());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(CHAT_CREATED_SUCCESSFULLY, response));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<ApiResponse<List<ChatDTO>>> getAll(@RequestBody UserUuidRequestDTO request) {
        List<ChatDTO> chatList = getChatUseCase.getAll(request.userUuid());
        return ResponseEntity.ok(new ApiResponse<>(FETCH_CHATS, chatList));
    }

    @GetMapping(GET_CHAT)
    public ResponseEntity<ApiResponse<ChatDetailDTO>> get(@PathVariable("chatUuid") UUID chatUuid) {
        ChatDetailDTO chat = getChatUseCase.get(chatUuid);
        return ResponseEntity.ok(new ApiResponse<>(FETCH_CHAT, chat));
    }
}
