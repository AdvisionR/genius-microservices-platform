package com.iotiq.api.controller;

import com.iotiq.api.dto.ApiResponse;
import com.iotiq.api.dto.CreateMessageRequestDTO;
import com.iotiq.api.dto.CreateMessageResponseDTO;
import com.iotiq.application.usecase.CreateMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.iotiq.infrastructure.config.RestApis.CREATE;
import static com.iotiq.infrastructure.config.RestApis.MESSAGE;
import static com.iotiq.message.ResponseMessages.MESSAGE_CREATED_SUCCESSFULLY;

@RestController
@RequiredArgsConstructor
@RequestMapping(MESSAGE)
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final CreateMessageUseCase createMessageUseCase;

    @PostMapping(CREATE)
    public ResponseEntity<ApiResponse<CreateMessageResponseDTO>> create(@RequestBody CreateMessageRequestDTO request) {
        CreateMessageResponseDTO response = createMessageUseCase.create(request);
        logger.info("New message created: {}", response.getUuid());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(MESSAGE_CREATED_SUCCESSFULLY, response));
    }
}
