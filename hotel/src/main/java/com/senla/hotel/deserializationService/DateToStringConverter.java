package com.senla.hotel.deserializationService;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Date;

public class DateToStringConverter extends StdConverter<Date, String> {
    @Override
    public String convert(Date value) {
        // Convert Date to a String representation (customize as needed)
        // Here, we'll use ISO 8601 format
        return value.toInstant().toString();
    }
}
