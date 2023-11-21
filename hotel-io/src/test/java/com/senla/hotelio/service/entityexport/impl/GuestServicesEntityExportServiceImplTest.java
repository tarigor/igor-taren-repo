package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hoteldb.entity.RoomService;
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

import static com.senla.hotel.enums.ServiceType.CLEANING;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServicesEntityExportServiceImplTest {
    public static final String PATTERN = "dd-MM-yyyy";
    private final String ENTITY_FILENAME = "GuestServices";
    private final String FILE_PATH = "src/test/resources/csv/export/";
    @Mock
    private GuestServicesServiceImpl guestServicesService;
    @InjectMocks
    private GuestServicesEntityExportServiceImpl guestServicesEntityExportService;
    private List<GuestServices> guestServicesList;

    @BeforeEach
    void setUp() throws ParseException {
        Guest guest1 = new Guest(1L, "Ivan", "Ivanov", "ivnov@mail.com", "", "");
        RoomService roomService1 = new RoomService(1L, CLEANING.name(), 12.3);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        GuestServices guestServices1 = new GuestServices(1L, guest1, roomService1, LocalDate.parse("22-09-2023", dateTimeFormatter));
        guestServicesList = Arrays.asList(guestServices1);
    }

    @Test
    void exportEntityFileExistTest() {
        guestServicesEntityExportService.setCsvExportPath(FILE_PATH);

        when(guestServicesService.getAll()).thenReturn(guestServicesList);

        guestServicesEntityExportService.exportEntity();

        assertTrue(Files.exists(Path.of(FILE_PATH)));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(FILE_PATH + ENTITY_FILENAME + ".csv"));
    }
}