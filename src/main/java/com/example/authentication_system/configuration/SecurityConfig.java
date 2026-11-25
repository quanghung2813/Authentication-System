package com.example.authentication_system.configuration;

import com.example.authentication_system.custom.CustomAccessDeniedHandler;
import com.example.authentication_system.custom.CustomAuthEntryPoint;
import com.example.authentication_system.dto.response.ApiResponse;
import com.example.authentication_system.exception.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.HandlerExceptionResolver;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // Cho phép externalize cấu hình strength qua application.yml (mặc định là 12)
    @Value("${security.password.bcrypt-strength:12}")
    private int bcryptStrength;

    private final ObjectMapper objectMapper;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Định danh thuật toán mặc định dùng để encode mật khẩu mới
        String defaultEncoderId = "bcrypt";

        // Đăng ký danh sách các thuật toán được hỗ trợ khi đối chiếu (matches)
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        // Khởi tạo BCrypt với strength = 12 làm thuật toán mặc định
        encoders.put("bcrypt", new BCryptPasswordEncoder(bcryptStrength));

        DelegatingPasswordEncoder delegatingEncoder = new DelegatingPasswordEncoder(defaultEncoderId, encoders);
        return delegatingEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CustomAuthEntryPoint authenticationEntryPoint,
            CustomAccessDeniedHandler accessDeniedHandler) {
        http.exceptionHandling(e -> e
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );
        return http.build();
    }
}
