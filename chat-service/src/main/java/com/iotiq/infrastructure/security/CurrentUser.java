package com.iotiq.infrastructure.security;

import com.iotiq.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CurrentUser {
    private String token;
    private String userName;
    private String email;
    private UserRole role;
}
