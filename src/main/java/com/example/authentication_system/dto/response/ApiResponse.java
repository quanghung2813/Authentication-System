package com.example.authentication_system.dto.response;

import com.example.authentication_system.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
// Tự động bỏ qua các trường có giá trị null khi serialize sang JSON
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T> {
    boolean success;
    int code = 1000;
    String message;
    T result;
    Map<String, String> errors;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone ="Asia")
    LocalDateTime timestamp = LocalDateTime.now();

    public static <T> ApiResponse<T> success(T result, String message) {
        return new ApiResponse<>(true, 1000, message, result, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<>(false, errorCode.getCode(), errorCode.getMessage(), null, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(false, code, message, null, null, LocalDateTime.now());
    }
    public static <T> ApiResponse<T> validationError(int code, String message, Map<String, String> errors) {
        return new ApiResponse<>(false, code, message, null, errors, LocalDateTime.now());
    }
}
