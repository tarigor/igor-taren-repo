package com.senla.hotel.deserializationService;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Date;

public class DateToStringConverter extends StdConverter<Date, String> {
    @Override
    public String convert(Date value) {
        return value.toInstant().toString();
    }
}
