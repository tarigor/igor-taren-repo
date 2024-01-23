package com.senla.adsservice.validator.annotation;

import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Pattern(regexp = "")
@Retention(RetentionPolicy.RUNTIME)
@interface DateTimeFormat {
    String message() default "Invalid date-time format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
