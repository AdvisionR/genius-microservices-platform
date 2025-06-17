package com.iotiq.api.dto;

public record LoginRequestDTO(
        String userName,
        String password
) {
}
