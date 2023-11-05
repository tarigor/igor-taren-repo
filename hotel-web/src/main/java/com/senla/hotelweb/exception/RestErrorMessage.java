package com.senla.hotelweb.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class RestErrorMessage {
    private String time;
    private String message;
    private String errorDetails;

    public RestErrorMessage(String message, String errorDetails) {
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss"));
        this.message = message;
        this.errorDetails = errorDetails;
    }
}
