package com.iotiq.infrastructure.config;

import com.iotiq.security.jwt.JwtProperties;
import com.iotiq.security.jwt.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    private final JwtProperties jwtProperties;

    public JwtConfig(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService(jwtProperties);
    }
}
