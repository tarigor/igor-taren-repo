package com.senla.hotel.deserializationservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;


@ExtendWith(MockitoExtension.class)
class StringToDateConverterTest {

    @InjectMocks
    private StringToDateConverter stringToDateConverter;

    @Test
    void convert() {
        String dateString = "Nov 11, 2023, 01:30:00 PM";

        Date result = stringToDateConverter.convert(dateString);

        Assertions.assertNotNull(result, "Parsed date should not be null");
        Assertions.assertEquals("Sat Nov 11 13:30:00 CET 2023", result.toString(),
                "Parsed date does not match the expected date");
    }
}