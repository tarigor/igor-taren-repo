package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hoteldb.entity.Guest;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestEntityExportServiceImplTest {
    private static final String ENTITY_FILENAME = "guest";
    @Mock
    private GuestServiceImpl guestService;
    @InjectMocks
    private GuestEntityExportServiceImpl guestEntityExportService;
    private List<Guest> guests;

    @BeforeEach
    void setUp() {
        Guest guest1 = new Guest(1L, "Ivan", "Ivanov", "ivnov@mail.com", "", "");
        Guest guest2 = new Guest(2L, "Petr", "Petrov", "petrov@mail.com", "", "");
        Guest guest3 = new Guest(2L, "Sidr", "Sidorov", "sidorov@mail.com", "", "");
        guests = List.of(guest1, guest2, guest3);
    }

    @Test
    void exportEntity() throws IOException, HotelIoModuleException {
        String filePath = "";
        ReflectionTestUtils.setField(guestEntityExportService, "ENTITY_FILENAME", "Guest");
        ReflectionTestUtils.setField(guestEntityExportService, "csvExportPath", filePath);

        when(guestService.getAll()).thenReturn(guests);

        guestEntityExportService.exportEntity();

        assertTrue(Files.exists(Path.of(filePath)));
        assertTrue(Files.deleteIfExists(Path.of(filePath + ENTITY_FILENAME + ".csv")));
    }
}