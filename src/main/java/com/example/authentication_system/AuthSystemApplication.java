package com.example.authentication_system;

import com.example.authentication_system.duration.JwtProperties;
import com.example.authentication_system.duration.PasswordResetProperties;
import com.example.authentication_system.duration.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableConfigurationProperties({
        JwtProperties.class,
        SecurityProperties.class,
        PasswordResetProperties.class
})
public class AuthSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthSystemApplication.class, args);
    }

}
