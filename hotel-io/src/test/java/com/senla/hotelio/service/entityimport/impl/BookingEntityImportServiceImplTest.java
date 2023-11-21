package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.util.EntityDtoMapper;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hotelio.service.entityimport.ImportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookingEntityImportServiceImplTest {
    @Mock
    private ImportService importService;
    @Mock
    private RoomServiceImpl roomService;
    @Mock
    private GuestServiceImpl guestService;
    @Mock
    private EntityDtoMapper entityDtoMapper;
    @InjectMocks
    private BookingEntityImportServiceImpl bookingEntityImportService;
    private Guest guest1, guest2;
    private RoomDto room1, room2;

    @BeforeEach
    void setUp() {
        guest1 = new Guest(1L, "Ivan", "Ivanov", "ivanov@mail.com", "pass", "role");
        guest2 = new Guest(2L, "Petr", "Petrov", "petrov@mail.com", "pass", "role");

        room1 = new RoomDto(1L, 1, 11.1, "OCCUPIED", 1);
        room2 = new RoomDto(2L, 1, 34.2, "OCCUPIED", 1);
    }

    @Test
    void importEntitiesTest() throws IOException {
        String fileName = "Booking";
        String inputCsv = "1,1,1,2023-09-10,2023-09-13";
//        Booking bookingToBeChecked = new Booking(
//                1L,
//                guest1,
//                new Room(1L, 1, 11.1, "OCCUPIED", 1),
//                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-10"),
//                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-13"));
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"))) {
//            writer.write(inputCsv);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        ReflectionTestUtils.setField(bookingEntityImportService, "ENTITY_NAME", fileName);
//        ReflectionTestUtils.setField(bookingEntityImportService, "csvImportPath", "");

//        importService.setCsvImportPath("src/test/resources/csv/import");

//        when(guestService.getById(guest1.getId())).thenReturn(guest1);
//        when(roomService.getRoom(room1.getId())).thenReturn(room1);
//        when(entityDtoMapper.convertFromDtoToEntity(room1, Room.class)).thenReturn(
//                new Room(room1.getId(), room1.getCapacity(), room1.getPrice(), room1.getRoomStatus(), room1.getStarsRating()));

        List<Booking> bookingsResult = bookingEntityImportService.importEntities();

        bookingsResult.forEach(System.out::println);
//        assertEquals(List.of(bookingToBeChecked), bookingsResult);
//        assertTrue(Files.deleteIfExists(Path.of(fileName + ".csv")));
    }
}