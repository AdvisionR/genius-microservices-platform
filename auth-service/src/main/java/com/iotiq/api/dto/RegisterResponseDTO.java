package com.iotiq.api.dto;

import com.iotiq.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {
    private UUID uuid;
    private String userName;
    private String email;
    private UserRole role;
}