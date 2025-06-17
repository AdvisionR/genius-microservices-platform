package com.iotiq.api.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateUserRequestDTO(
    UUID authUuid,
    String userName,
    String email
) {
}
