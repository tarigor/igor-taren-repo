package com.senla.serialization.service;

import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hoteldb.entity.Room;
import com.senla.hoteldb.entity.RoomService;
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
class SerializationServiceTest {
    @Mock
    private RoomServicesServiceImpl roomServicesService;
    @Mock
    private GuestServiceImpl guestService;
    @Mock
    private GuestServicesServiceImpl guestServicesService;
    @Mock
    private RoomServiceImpl roomService;
    @Mock
    private BookingServiceImpl bookingService;
    @InjectMocks
    private SerializationService serializationService;

    @Test
    void serialize() throws IOException {

        List<String> enities = List.of("Booking", "Guest", "GuestService", "Room", "RoomService");

        ReflectionTestUtils.setField(serializationService, "jsonExportPath", "");
        when(bookingService.getAll()).thenReturn(List.of(new Booking()));
        when(guestService.getAll()).thenReturn(List.of(new Guest()));
        when(guestServicesService.getAll()).thenReturn(List.of(new GuestServices()));
        when(roomService.getAll()).thenReturn(List.of(new Room()));
        when(roomServicesService.getAll()).thenReturn(List.of(new RoomService()));

        for (String entity : enities) {
            serializationService.serialize(entity);
        }

        for (String entity : enities) {
            assertTrue(Files.exists(Path.of(entity + ".json")));
            assertTrue(Files.deleteIfExists(Path.of(entity + ".json")));
        }
    }
}