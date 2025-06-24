package com.iotiq.infrastructure.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperties {
    private List<String> allowedEndpoints;

    public String[] getAllowedEndpoints() {
        return allowedEndpoints.toArray(new String[0]);
    }
}