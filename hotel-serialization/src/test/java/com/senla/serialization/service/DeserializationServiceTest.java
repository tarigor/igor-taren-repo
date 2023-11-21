package com.senla.serialization.service;

import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DeserializationServiceTest {

    private final HashMap<Long, Booking> bookingMap = new HashMap<>();
    @InjectMocks
    private DeserializationService deserializationService;

    @BeforeEach
    void setUp() throws ParseException {
        Guest guest1 = new Guest(1L, "Igor", "Ivanov", "ivanov@mail.com", "pass", "role");
        Guest guest2 = new Guest(2L, "Sergei", "Petrov", "spetrov@mail.com", "pass", "role");
        Room room1 = new Room(1L, 1, 11.1, "OCCUPIED", 1);
        Room room2 = new Room(2L, 1, 21.1, "OCCUPIED", 1);
        Booking booking1 = new Booking(1L, guest1, room1, new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-11"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-12"));
        Booking booking2 = new Booking(2L, guest2, room2, new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-15"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-16"));
        bookingMap.put(booking1.getId(), booking1);
        bookingMap.put(booking2.getId(), booking2);
    }

    @Test
    void deserializeTest() {
        deserializationService.setJsonImportPath("src/test/resources/json/import/");

        HashMap<Long, Booking> result = (HashMap<Long, Booking>) deserializationService.deserialize("booking");
        assertEquals(bookingMap, result);
    }
}