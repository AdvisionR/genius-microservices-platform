package com.iotiq.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
    @NotBlank String userName,
    @NotBlank String password,
    @NotBlank String rePassword,
    @NotBlank @Email String email
) {}
