package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.util.EntityDtoMapper;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingEntityImportServiceImplTest {
    public static final String PATTERN = "dd-MM-yyyy";
    public static final String CSV_IMPORT_PATH = "src/test/resources/csv/import/";
    @Mock
    private RoomServiceImpl roomService;
    @Mock
    private GuestServiceImpl guestService;
    @Mock
    private EntityDtoMapper entityDtoMapper;
    @InjectMocks
    private BookingEntityImportServiceImpl bookingEntityImportService;
    private Guest guest;
    private RoomDto room;

    @BeforeEach
    void setUp() {
        guest = new Guest(1L, "Ivan", "Ivanov", "ivanov@mail.com", "pass", "role");
        room = new RoomDto(1L, 1, 11.1, "OCCUPIED", 1);
    }

    @Test
    void importEntitiesTest() throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        Booking bookingToBeChecked = new Booking(
                1L,
                guest,
                new Room(1L, 1, 11.1, "OCCUPIED", 1),
                LocalDate.parse("10-09-2023", dateTimeFormatter),
                LocalDate.parse("13-09-2023", dateTimeFormatter));

        bookingEntityImportService.setCsvImportPath(CSV_IMPORT_PATH);

        when(guestService.getById(guest.getId())).thenReturn(guest);
        when(roomService.getRoom(room.getId())).thenReturn(room);
        when(entityDtoMapper.convertFromDtoToEntity(room, Room.class)).thenReturn(
                new Room(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating()));

        List<Booking> bookingsResult = bookingEntityImportService.importEntities();

        assertEquals(List.of(bookingToBeChecked), bookingsResult);
    }
}