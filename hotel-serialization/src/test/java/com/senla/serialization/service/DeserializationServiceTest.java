package com.senla.serialization.service;

import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void deserialize() throws IOException {
        String jsonContent = "{\n" +
                "  \"1\": {\n" +
                "    \"id\": 1,\n" +
                "    \"guest\": {\n" +
                "      \"id\": 1,\n" +
                "      \"firstName\": \"Igor\",\n" +
                "      \"lastName\": \"Ivanov\",\n" +
                "      \"email\": \"ivanov@mail.com\",\n" +
                "      \"password\": \"pass\",\n" +
                "      \"role\": \"role\"\n" +
                "    },\n" +
                "    \"room\": {\n" +
                "      \"id\": 1,\n" +
                "      \"capacity\": 1,\n" +
                "      \"price\": 11.1,\n" +
                "      \"roomStatus\": \"OCCUPIED\",\n" +
                "      \"starsRating\": 1\n" +
                "    },\n" +
                "    \"checkInDate\": \"11-11-2023\",\n" +
                "    \"checkOutDate\": \"12-11-2023\"\n" +
                "  },\n" +
                "  \"2\": {\n" +
                "    \"id\": 2,\n" +
                "    \"guest\": {\n" +
                "      \"id\": 2,\n" +
                "      \"firstName\": \"Sergei\",\n" +
                "      \"lastName\": \"Petrov\",\n" +
                "      \"email\": \"spetrov@mail.com\",\n" +
                "      \"password\": \"pass\",\n" +
                "      \"role\": \"role\"\n" +
                "    },\n" +
                "    \"room\": {\n" +
                "      \"id\": 2,\n" +
                "      \"capacity\": 1,\n" +
                "      \"price\": 21.1,\n" +
                "      \"roomStatus\": \"OCCUPIED\",\n" +
                "      \"starsRating\": 1\n" +
                "    },\n" +
                "    \"checkInDate\": \"15-11-2023\",\n" +
                "    \"checkOutDate\": \"16-11-2023\"\n" +
                "  }\n" +
                "}";

        String filePath = "booking.json";

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonContent);
            System.out.println("JSON written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReflectionTestUtils.setField(deserializationService, "jsonImportPath", "");

        HashMap<Long, Booking> result = (HashMap<Long, Booking>) deserializationService.deserialize("booking");

        assertEquals(bookingMap, result);
        assertTrue(Files.exists(Path.of(filePath)));
        assertTrue(Files.deleteIfExists(Path.of(filePath)));
    }
}