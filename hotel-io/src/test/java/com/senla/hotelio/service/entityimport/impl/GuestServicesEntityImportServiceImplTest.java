package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hoteldb.entity.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.senla.hotel.enums.ServiceType.CLEANING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServicesEntityImportServiceImplTest {
    public static final String PATTERN = "dd-MM-yyyy";
    public static final String CSV_IMPORT_PATH = "src/test/resources/csv/import/";
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        guestServices = new GuestServices(1L, guest, roomService, LocalDate.parse("22-09-2023", dateTimeFormatter));
    }

    @Test
    void importEntitiesTest() {
        GuestServices guestServiceToBeChecked = guestServices;

        guestServicesEntityImportService.setCsvImportPath(CSV_IMPORT_PATH);

        when(guestService.getById(guest.getId())).thenReturn(guest);
        when(roomServicesService.getById(roomService.getId())).thenReturn(roomService);

        List<GuestServices> result = guestServicesEntityImportService.importEntities();

        assertEquals(List.of(guestServiceToBeChecked), result);
    }
}