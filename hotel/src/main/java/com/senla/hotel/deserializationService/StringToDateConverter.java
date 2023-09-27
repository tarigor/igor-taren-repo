package com.senla.hotel.deserializationService;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            e.printStackTrace();
        }
        return date;
    }
}
