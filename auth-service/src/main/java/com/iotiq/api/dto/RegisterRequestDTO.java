package com.iotiq.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
    @NotBlank String userName,
    @NotBlank String password,
    @NotBlank String rePassword,
    @NotBlank @Email String email
) {}
