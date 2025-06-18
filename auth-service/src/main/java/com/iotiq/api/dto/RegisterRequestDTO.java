package com.iotiq.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String rePassword;

    @NotBlank @Email
    private String email;
}
