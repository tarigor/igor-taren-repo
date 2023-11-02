package com.senla.hotelweb.validator;

import com.senla.hotelweb.validator.annotation.EnumValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EnumValidatorValidator implements ConstraintValidator<EnumValidator, String> {
    private EnumValidator enumValidator;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        this.enumValidator = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Class<? extends Enum<?>> enumClass = enumValidator.targetClassType();
        Enum<?>[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants != null) {
            for (Enum<?> enumValue : enumConstants) {
                if (enumValue.name().equals(value)) {
                    return true;
                }
            }
        }
        String message = Arrays.stream(enumConstants)
                .map(Enum::name)
                .collect(Collectors.joining(", "));

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Invalid value. Valid options are: " + message)
                .addConstraintViolation();
        return false;
    }
}