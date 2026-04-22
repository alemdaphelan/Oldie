package com.oldie.backend.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "Password is required")
    String password;

    @NotBlank(message = "Phone number is required")
    String phoneNumber;

    @NotBlank(message = "City GSO ID is required")
    Integer cityGsoId;

    @NotBlank(message = "District GSO ID is required")
    Integer districtGsoId;

    @NotBlank(message = "Ward GSO ID is required")
    Integer wardGsoId;
}