package com.example.jira.duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "app.password-reset")
    public record PasswordResetProperties(
            Duration ttl
    ) {}