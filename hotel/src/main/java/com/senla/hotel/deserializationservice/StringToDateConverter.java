package com.senla.hotel.deserializationservice;

import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class StringToDateConverter extends StdConverter<String, Date> {

    @Override
    public Date convert(String value) {
        String pattern = "MMM d, yyyy, hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(value);
            System.out.println("Parsed Date: " + date);
        } catch (ParseException e) {
            log.error("an error occurred during a parse operation -> {}", e.getMessage());
        }
        return date;
    }
}
