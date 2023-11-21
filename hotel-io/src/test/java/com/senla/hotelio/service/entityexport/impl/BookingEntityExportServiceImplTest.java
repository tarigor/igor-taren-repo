package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
import com.senla.hotelio.service.entityexport.ExportService;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingEntityExportServiceImplTest {
    private static final String ENTITY_FILENAME = "booking";
    private static final String FILE_PATH = "src/test/resources/csv/export/";
    @Mock
    private ExportService exportService;
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

        Booking booking1 = new Booking(1L, guest1, room1, new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-11"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-12"));
        Booking booking2 = new Booking(2L, guest2, room2, new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-13"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-15"));
        Booking booking3 = new Booking(3L, guest1, room1, new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-16"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-19"));

        bookings = Arrays.asList(booking1, booking2, booking3);
    }

    @Test
    void exportEntityFileExistTest() {
        exportService.setCsvExportPath(FILE_PATH);

        when(bookingService.getAll()).thenReturn(bookings);

        bookingEntityExportService.exportEntity();

        assertTrue(Files.exists(Path.of(FILE_PATH)));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(FILE_PATH + ENTITY_FILENAME + ".csv"));
    }
}