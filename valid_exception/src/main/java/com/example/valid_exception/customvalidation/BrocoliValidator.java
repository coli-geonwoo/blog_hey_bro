package com.example.valid_exception.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BrocoliValidator implements ConstraintValidator<Brocoli, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        return value.matches("^brocoli.*$"); // "brocoli"로 시작하는 지 검증
    }
}
