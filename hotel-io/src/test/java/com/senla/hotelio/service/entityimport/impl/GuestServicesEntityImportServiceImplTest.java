package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.senla.hotel.enums.ServiceType.CLEANING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServicesEntityImportServiceImplTest {

    @Mock
    private GuestServiceImpl guestService;
    @Mock
    private RoomServicesServiceImpl roomServicesService;
    @InjectMocks
    private GuestServicesEntityImportServiceImpl guestServicesEntityImportService;
    private GuestServices guestServices;
    private RoomService roomService;
    private Guest guest;

    @BeforeEach
    void setUp() throws ParseException {
        guest = new Guest(1L, "Ivan", "Ivanov", "ivnov@mail.com", "", "");
        roomService = new RoomService(1L, CLEANING.name(), 12.3);
        guestServices = new GuestServices(1L, guest, roomService, new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-22"));
    }

    @Test
    void importEntities() throws IOException, HotelIoModuleException {
        String fileName = "GuestServices";
        String inputCsv = "1,1,1,2023-09-22,0.0";
        GuestServices guestServiceToBeChecked = guestServices;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"))) {
            writer.write(inputCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReflectionTestUtils.setField(guestServicesEntityImportService, "ENTITY_NAME", fileName);
        ReflectionTestUtils.setField(guestServicesEntityImportService, "csvImportPath", "");

        when(guestService.getById(guest.getId())).thenReturn(guest);
        when(roomServicesService.getById(roomService.getId())).thenReturn(roomService);

        List<GuestServices> result = guestServicesEntityImportService.importEntities();

        assertEquals(List.of(guestServiceToBeChecked), result);
        assertTrue(Files.deleteIfExists(Path.of(fileName + ".csv")));
    }
}