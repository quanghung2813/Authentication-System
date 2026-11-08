package com.example.jira.duration;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(
        @NotBlank(message = "JWT must not be empty or blank!")
        String secret,
        String issuer,
        String audience,
        Duration accessTtl,
        Duration refreshTtl,
        Duration refreshGrace
) {
}
