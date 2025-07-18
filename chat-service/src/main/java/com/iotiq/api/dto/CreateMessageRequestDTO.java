package com.iotiq.api.dto;

import com.iotiq.domain.model.Sender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequestDTO {
    private UUID chatUuid;
    private String content;
    private Sender sender;
}
