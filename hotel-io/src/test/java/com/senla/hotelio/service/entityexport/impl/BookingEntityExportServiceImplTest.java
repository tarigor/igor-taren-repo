package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingEntityExportServiceImplTest {
    public static final String PATTERN = "dd-MM-yyyy";
    private static final String ENTITY_FILENAME = "booking";
    private static final String FILE_PATH = "src/test/resources/csv/export/";
    @Mock
    private BookingServiceImpl bookingService;
    @InjectMocks
    private BookingEntityExportServiceImpl bookingEntityExportService;
    private List<Booking> bookings;

    @BeforeEach
    void setUp() throws ParseException {
        Guest guest1 = new Guest(1L, "Ivan", "Ivanov", "ivanov@mail.com", "pass", "role");
        Guest guest2 = new Guest(2L, "Petr", "Petrov", "petrov@mail.com", "pass", "role");

        Room room1 = new Room(1L, 1, 11.1, "OCCUPIED", 1);
        Room room2 = new Room(2L, 1, 34.2, "OCCUPIED", 1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        Booking booking1 = new Booking(1L, guest1, room1, LocalDate.parse("11-11-2023", dateTimeFormatter),
                LocalDate.parse("12-11-2023", dateTimeFormatter));
        Booking booking2 = new Booking(2L, guest2, room2, LocalDate.parse("13-11-2023", dateTimeFormatter),
                LocalDate.parse("15-11-2023", dateTimeFormatter));
        Booking booking3 = new Booking(3L, guest1, room1, LocalDate.parse("16-09-2023", dateTimeFormatter),
                LocalDate.parse("19-09-2023", dateTimeFormatter));

        bookings = Arrays.asList(booking1, booking2, booking3);
    }

    @Test
    void exportEntityFileExistTest() {
        bookingEntityExportService.setCsvExportPath(FILE_PATH);

        when(bookingService.getAll()).thenReturn(bookings);

        bookingEntityExportService.exportEntity();

        assertTrue(Files.exists(Path.of(FILE_PATH)));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(FILE_PATH + ENTITY_FILENAME + ".csv"));
    }
}