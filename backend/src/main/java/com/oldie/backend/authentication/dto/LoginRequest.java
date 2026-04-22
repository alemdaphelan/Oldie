package com.oldie.backend.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class LoginRequest {
    @NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "Password is required")
    String password;
}