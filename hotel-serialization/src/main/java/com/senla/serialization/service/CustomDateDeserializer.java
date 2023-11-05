package com.senla.serialization.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String dateString = "";
        Date date = null;
        try {
            dateString = jsonParser.getText();
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            log.error("an error occurred while parsing date in format:{} -> {} , allowed format: yyyy-MM-dd", dateString, e.getMessage());
        } catch (IOException e) {
            log.error("an error occurred during IO operation -> {}", e.getMessage());
        }
        return date;
    }
}