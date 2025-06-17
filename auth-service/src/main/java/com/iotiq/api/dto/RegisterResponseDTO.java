package com.iotiq.api.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RegisterResponseDTO(
        UUID uuid,
        String userName,
        String email
) {
}
