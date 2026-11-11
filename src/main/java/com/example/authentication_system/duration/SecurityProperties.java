package com.example.authentication_system.duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "app.security.login-limit")
    public record SecurityProperties(
            int perEmailIp,
            int perIp,
            Duration window
    ) {}