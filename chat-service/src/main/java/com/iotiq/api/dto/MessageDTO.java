package com.iotiq.api.dto;

import com.iotiq.domain.model.Sender;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class MessageDTO {
    private UUID uuid;
    private Sender sender;
    private String content;
    private Instant createdAt;
}