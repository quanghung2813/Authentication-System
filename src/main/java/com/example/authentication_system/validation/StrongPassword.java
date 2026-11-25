package com.example.authentication_system.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "Password must be 8–64 characters long and include uppercase letters, lowercase letters, and digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
