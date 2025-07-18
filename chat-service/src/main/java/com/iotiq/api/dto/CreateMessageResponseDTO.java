package com.iotiq.api.dto;

import com.iotiq.domain.model.Message;
import com.iotiq.domain.model.Sender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageResponseDTO {
    private UUID uuid;
    private UUID chatUuid;
    private String content;
    private Instant createdAt;
    private Sender sender;
}
