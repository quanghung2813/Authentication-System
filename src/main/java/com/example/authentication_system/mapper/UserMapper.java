package com.example.authentication_system.mapper;

import com.example.authentication_system.dto.request.RegisterRequest;
import com.example.authentication_system.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest request);
}
