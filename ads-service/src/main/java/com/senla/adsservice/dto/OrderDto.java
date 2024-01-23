package com.senla.adsservice.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotEmpty
    @Digits(integer = 5, fraction = 0, message = "Please enter a valid integer with up to 5 digit")
    private long advId;
    @NotEmpty
    @Digits(integer = 5, fraction = 0, message = "Please enter a valid integer with up to 5 digit")
    private long sellerId;
    @NotEmpty
    @DateTimeFormat
    private LocalDate date;
}
