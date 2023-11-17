package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hoteldb.entity.RoomService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.senla.hotel.enums.ServiceType.CLEANING;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServicesEntityExportServiceImplTest {
    private final String ENTITY_FILENAME = "GuestServices";
    @Mock
    private GuestServicesServiceImpl guestServicesService;
    @InjectMocks
    private GuestServicesEntityExportServiceImpl guestServicesEntityExportService;
    private List<GuestServices> guestServicesList;

    @BeforeEach
    void setUp() throws ParseException {
        Guest guest1 = new Guest(1L, "Ivan", "Ivanov", "ivnov@mail.com", "", "");
        RoomService roomService1 = new RoomService(1L, CLEANING.name(), 12.3);
        GuestServices guestServices1 = new GuestServices(1L, guest1, roomService1, new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-22"));
        guestServicesList = List.of(guestServices1);
    }

    @Test
    void exportEntity() throws HotelIoModuleException, IOException {
        String filePath = "";
        ReflectionTestUtils.setField(guestServicesEntityExportService, "ENTITY_FILENAME", "GuestServices");
        ReflectionTestUtils.setField(guestServicesEntityExportService, "csvExportPath", filePath);

        when(guestServicesService.getAll()).thenReturn(guestServicesList);

        guestServicesEntityExportService.exportEntity();

        assertTrue(Files.exists(Path.of(filePath)));
        assertTrue(Files.deleteIfExists(Path.of(filePath + ENTITY_FILENAME + ".csv")));
    }
}