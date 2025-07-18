package com.iotiq.api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ChatDetailDTO {
    private UUID uuid;
    private String title;
    private Instant createdAt;
    private List<MessageDTO> messages;
}