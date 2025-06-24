package com.iotiq.dto;

import com.iotiq.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDTO {
    private UUID uuid;
    private UUID authUuid;
    private String userName;
    private String email;
    private UserRole role;
    private String name;
    private String phone;
    private String avatar;
}
