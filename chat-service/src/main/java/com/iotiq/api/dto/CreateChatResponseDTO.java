package com.iotiq.api.dto;

import com.iotiq.domain.model.Message;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CreateChatResponseDTO {
    private UUID uuid;
    private String title;
    private UUID userUuid;
    private Instant createdAt;
    private List<Message> messages;
}
