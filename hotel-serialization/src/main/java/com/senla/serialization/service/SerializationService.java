package com.senla.serialization.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import com.senla.serialization.enums.EntityName;
import com.senla.serialization.exception.HotelSerializationModuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SerializationService {
    public static final String EXTENSION_JSON = ".json";
    @Value("${json.export.path}")
    private String jsonExportPath;
    private RoomServicesServiceImpl roomServicesService;
    private BookingServiceImpl bookingService;
    private GuestServiceImpl guestService;
    private GuestServicesServiceImpl guestServicesService;
    private RoomServiceImpl roomService;

    @Autowired
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @Autowired
    public void setGuestService(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @Autowired
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    @Autowired
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setRoomServicesService(RoomServicesServiceImpl roomServicesService) {
        this.roomServicesService = roomServicesService;
    }

    public void serialize(String entityNameString) {
        try {
            EntityName entityName = EntityName.valueOf(entityNameString.toUpperCase());
            switch (entityName) {
                case BOOKING -> serializeMap(bookingService.getAll().stream()
                        .collect(Collectors.toMap(Booking::getId, booking -> booking)), "Booking");
                case GUEST -> serializeMap(guestService.getAll().stream()
                        .collect(Collectors.toMap(Guest::getId, guest -> guest)), "Guest");
                case GUESTSERVICE -> serializeMap(guestServicesService.getAll().stream()
                        .collect(Collectors.toMap(GuestServices::getId, guestServices -> guestServices)), "GuestServices");
                case ROOM -> serializeMap(roomService.getAll().stream()
                        .collect(Collectors.toMap(Room::getId, room -> room)), "Room");
                case ROOMSERVICE -> serializeMap(roomServicesService.getAll().stream()
                        .collect(Collectors.toMap(RoomService::getId, roomService -> roomService)), "RoomServices");
                default -> log.error("There is no such an entity -> {}", entityName);
            }
        } catch (HotelSerializationModuleException e) {
            throw new IllegalArgumentException("");
        }
    }

    private void serializeMap(Map<Long, ?> map, String fileName) throws HotelSerializationModuleException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("dd-MM-yyyy")
                .create();
        try (FileWriter writer = new FileWriter(jsonExportPath + fileName + EXTENSION_JSON)) {
            gson.toJson(map, writer);
            log.info("Serialization completed successfully");
        } catch (IOException e) {
            log.error("an error occurred during an IO operation -> {}", e.getMessage());
            throw new HotelSerializationModuleException(e);
        }
    }
}
