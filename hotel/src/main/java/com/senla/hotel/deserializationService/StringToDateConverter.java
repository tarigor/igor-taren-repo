package com.senla.hotel.deserializationService;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringToDateConverter extends StdConverter<String, Date> {
    private static final Logger logger = LoggerFactory.getLogger(StringToDateConverter.class);

    @Override
    public Date convert(String value) {
        String pattern = "MMM d, yyyy, hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(value);
            System.out.println("Parsed Date: " + date);
        } catch (ParseException e) {
            logger.error("an error occurred during a parse operation -> {}", e.getMessage());
            e.printStackTrace();
        }
        return date;
    }
}
