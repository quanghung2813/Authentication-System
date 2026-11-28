package com.example.authentication_system.dto.response;

import com.example.authentication_system.enums.Roles;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {
    String email;
    String fullName;
    String password;
    Set<Roles> roles;
}
