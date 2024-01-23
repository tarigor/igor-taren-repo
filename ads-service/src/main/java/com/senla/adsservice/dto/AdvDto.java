package com.senla.adsservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvDto {
    private long id;
    @NotNull
    @NotEmpty(message = "The field `sellerId` must be not empty")
    private long sellerId;
    @NotEmpty
    @Size(min = 3, max = 255, message = "`advMessage` must be between {min} and {max} characters long")
    private String advMessage;
    @NotEmpty
    private boolean priority;

    public AdvDto(long sellerId, String advMessage, boolean priority) {
        this.sellerId = sellerId;
        this.advMessage = advMessage;
        this.priority = priority;
    }
}
