package com.senla.serialization.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class CustomDateDeserializer extends JsonDeserializer<LocalDate> {

    public static final String PATTERN = "dd-MM-yyyy";

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        LocalDate date = null;
        try {
            String dateString = jsonParser.getText();
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(PATTERN));
        } catch (IOException e) {
            log.error("an error occurred during IO operation -> {}", e.getMessage());
        }
        return date;
    }
}