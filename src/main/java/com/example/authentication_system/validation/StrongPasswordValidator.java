package com.example.authentication_system.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
    private static final Pattern RULE =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,64}$");
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && RULE.matcher(value).matches();
    }
}
