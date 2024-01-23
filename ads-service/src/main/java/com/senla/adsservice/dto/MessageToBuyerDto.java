package com.senla.adsservice.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageToBuyerDto {
    private long id;
    @NotEmpty
    @Digits(integer = 5, fraction = 0, message = "Please enter a valid integer with up to 5 digit")
    private long buyerId;
    @NotEmpty
    @Size(min = 3, max = 255, message = "`message` must be between {min} and {max} characters long")
    private String message;
}
