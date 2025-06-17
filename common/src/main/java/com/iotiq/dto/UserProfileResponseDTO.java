package com.iotiq.dto;

import java.util.UUID;

public record UserProfileResponseDTO(
        UUID uuid,
        UUID authUuid,
        String userName,
        String email,
        String name,
        String phone,
        String avatar
) {
}
