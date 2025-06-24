package com.iotiq.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
    String secretKey,
    Long accessTokenExpirationMs,
    Long refreshTokenExpirationMs
) {}
