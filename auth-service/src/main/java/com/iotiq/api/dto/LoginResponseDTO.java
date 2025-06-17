package com.iotiq.api.dto;

public record LoginResponseDTO(
        String accessToken,
        String refreshToken
) {
}
