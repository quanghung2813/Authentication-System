package com.example.authentication_system.exception;

import com.example.authentication_system.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    ErrorCode errorCode;

    // Handler dự phòng cho lỗi không lường trước
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        e.printStackTrace();
        log.error("Unhandled system exception occurred: ", e);
        errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode));
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(AppException e) {
        e.printStackTrace();
        errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(
                        errorCode.getCode(),
                        errorCode.getMessage()
                ));
    }

    // Lỗi phân quyền @PreAuthorize
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDeniedException(AccessDeniedException e) {
        e.printStackTrace();
        errorCode = ErrorCode.ACCESS_DENIED;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode));
    }

    // Lỗi JSON sai cú pháp hoặc sai kiểu dữ liệu
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        errorCode = ErrorCode.VALIDATION_FAILED;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode));
    }

    // Lỗi Validation @Valid trên @RequestBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        Map<String, String> fieldError = new LinkedHashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            // Giữ lại thông báo lỗi đầu tiên nếu 1 field vi phạm nhiều rule
            fieldError.put(error.getField(), error.getDefaultMessage());
        }
        errorCode = ErrorCode.VALIDATION_FAILED;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.validationError(
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        fieldError
                ));
    }

    // Lỗi Validation trên @RequestParam / @PathVariable
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolationException(ConstraintViolationException e) {
        e.printStackTrace();
        Map<String, String> errors = new LinkedHashMap<>();
        e.getConstraintViolations().forEach(cv -> {
            errors.put(cv.getPropertyPath().toString(), cv.getMessage());
        });
        errorCode = ErrorCode.VALIDATION_FAILED;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.validationError(
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        errors
                ));
    }

    // Lỗi sai đường dẫn Api
    @ExceptionHandler(value = {NoHandlerFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<ApiResponse<Void>> handleNoHandlerFoundException(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(
                        404,
                        "The API path being accessed could not be found."
                ));
    }

    // Lỗi sau phương thức Http
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiResponse.error(
                        405,
                        "HTTP method not supported"
                ));
    }
}
