package com.serialization.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateDeserializer extends JsonDeserializer<Date> {
    private static final Logger logger = LoggerFactory.getLogger(CustomDateDeserializer.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String dateString = "";
        Date date = null;
        try {
            dateString = jsonParser.getText();
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.error("an error occurred while parsing date in format:{} -> {}", dateString, e.getMessage());
        } catch (IOException e) {
            logger.error("an error occurred during IO operation -> {}", e.getMessage());
        }
        return date;
    }
}