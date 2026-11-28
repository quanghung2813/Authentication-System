package com.example.authentication_system.dto.request;

import com.example.authentication_system.validation.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @Email
        @NotBlank
        @Size(max = 255)
        String email,
        @NotBlank
        @Size(max = 100)
        String fullName,
        @StrongPassword
        String password
) {}
