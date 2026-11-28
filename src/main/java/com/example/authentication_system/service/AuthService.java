package com.example.authentication_system.service;

import com.example.authentication_system.configuration.SecurityConfig;
import com.example.authentication_system.dto.request.RegisterRequest;
import com.example.authentication_system.dto.response.RegisterResponse;
import com.example.authentication_system.entity.User;
import com.example.authentication_system.enums.Roles;
import com.example.authentication_system.exception.AppException;
import com.example.authentication_system.exception.ErrorCode;
import com.example.authentication_system.mapper.UserMapper;
import com.example.authentication_system.repository.UserRepository;
import com.example.authentication_system.utils.Constance;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    UserMapper userMapper;
    UserRepository userRepository;
    SecurityConfig securityConfig;

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.ROLES_USER);
        user.setRoles(roles);
        user = userRepository.save(user);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
