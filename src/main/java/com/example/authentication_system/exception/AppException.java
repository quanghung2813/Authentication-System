package com.example.authentication_system.exception;

public class AppException extends RuntimeException {
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
