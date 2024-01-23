package com.senla.adsservice.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class SalesDto {
    @NotEmpty
    @Digits(integer = 5, fraction = 0, message = "Please enter a valid integer with up to 5 digit")
    private long advId;
    @NotEmpty
    @Digits(integer = 5, fraction = 0, message = "Please enter a valid integer with up to 5 digit")
    private long buyerId;
    @NotEmpty
    @DateTimeFormat
    private LocalDate date;
}
