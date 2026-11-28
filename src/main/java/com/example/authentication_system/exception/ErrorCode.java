package com.example.authentication_system.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    EMAIL_EXISTED(1001, "Registration with an existing email address", HttpStatus.CONFLICT),
    INVALID_CREDENTIALS(1002, "Incorrect email or password", HttpStatus.UNAUTHORIZED),
    TOO_MANY_ATTEMPTS(1003, "Exceeded login attempt limit based on email and IP, or based on IP alone", HttpStatus.TOO_MANY_REQUESTS),
    ACCOUNT_DISABLED(1004, "The Admin has locked the account", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1005, "Missing, incorrect, or expired access token", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED(1006, "Access denied", HttpStatus.FORBIDDEN),
    INVALID_REFRESH_TOKEN(1007, "Refresh token is unavailable or has expired", HttpStatus.UNAUTHORIZED),
    TOKEN_REUSE_DETECTED(1008, "A rotated token has been reused more than 20 seconds later", HttpStatus.UNAUTHORIZED),
    INVALID_RESET_TOKEN(1009, "Reset incorrect, expired, or used tokens", HttpStatus.BAD_REQUEST),
    CURRENT_PASSWORD_INCORRECT(1010, "Changing password, but the current password is incorrect", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1011, "Admin performed an action on a non-existent user", HttpStatus.NOT_FOUND),
    VALIDATION_FAILED(1012, "Request that bypasses Bean Validation", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(500, "An unexpected internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    int code;
    String message;
    HttpStatus httpStatus;
}
