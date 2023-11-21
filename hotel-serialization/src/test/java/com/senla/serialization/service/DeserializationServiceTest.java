package com.senla.serialization.service;

import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DeserializationServiceTest {
    public static final String PATTERN = "dd-MM-yyyy";
    private final HashMap<Long, Booking> bookingMap = new HashMap<>();
    @InjectMocks
    private DeserializationService deserializationService;

    @BeforeEach
    void setUp() throws ParseException {
        Guest guest1 = new Guest(1L, "Igor", "Ivanov", "ivanov@mail.com", "pass", "role");
        Guest guest2 = new Guest(2L, "Sergei", "Petrov", "spetrov@mail.com", "pass", "role");
        Room room1 = new Room(1L, 1, 11.1, "OCCUPIED", 1);
        Room room2 = new Room(2L, 1, 21.1, "OCCUPIED", 1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        Booking booking1 = new Booking(1L, guest1, room1, LocalDate.parse("11-11-2023", dateTimeFormatter),
                LocalDate.parse("12-11-2023", dateTimeFormatter));
        Booking booking2 = new Booking(2L, guest2, room2, LocalDate.parse("15-11-2023", dateTimeFormatter),
                LocalDate.parse("16-11-2023", dateTimeFormatter));
        bookingMap.put(booking1.getId(), booking1);
        bookingMap.put(booking2.getId(), booking2);
    }

    @Test
    void deserializeResultTest() {
        deserializationService.setJsonImportPath("src/test/resources/json/import/");

        HashMap<Long, Booking> result = (HashMap<Long, Booking>) deserializationService.deserialize("booking");
        assertEquals(bookingMap, result);
    }
}