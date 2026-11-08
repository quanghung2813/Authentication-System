package com.example.jira;

import com.example.jira.duration.JwtProperties;
import com.example.jira.duration.PasswordResetProperties;
import com.example.jira.duration.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
})
@EnableConfigurationProperties({
        JwtProperties.class,
        SecurityProperties.class,
        PasswordResetProperties.class
})
public class JiraApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiraApplication.class, args);
    }

}
