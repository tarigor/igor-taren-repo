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
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SerializationService {
    public static final String EXTENSION_JSON = ".json";
    private final RoomServicesServiceImpl roomServicesService;
    private final BookingServiceImpl bookingService;
    private final GuestServiceImpl guestService;
    private final GuestServicesServiceImpl guestServicesService;
    private final RoomServiceImpl roomService;
    @Value("${json.export.path}")
    private String jsonExportPath;

    @Autowired
    public SerializationService(RoomServicesServiceImpl roomServicesService,
                                BookingServiceImpl bookingService,
                                GuestServiceImpl guestService,
                                GuestServicesServiceImpl guestServicesService,
                                RoomServiceImpl roomService) {
        this.roomServicesService = roomServicesService;
        this.bookingService = bookingService;
        this.guestService = guestService;
        this.guestServicesService = guestServicesService;
        this.roomService = roomService;
    }

    public void setJsonExportPath(String jsonExportPath) {
        this.jsonExportPath = jsonExportPath;
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
                        .collect(Collectors.toMap(GuestServices::getId, guestServices -> guestServices)), "GuestService");
                case ROOM -> serializeMap(roomService.getAll().stream()
                        .collect(Collectors.toMap(Room::getId, room -> room)), "Room");
                case ROOMSERVICE -> serializeMap(roomServicesService.getAll().stream()
                        .collect(Collectors.toMap(RoomService::getId, roomService -> roomService)), "RoomService");
                default -> log.error("There is no such an entity -> {}", entityName);
            }
        } catch (HotelSerializationModuleException e) {
            throw new IllegalArgumentException("");
        }
    }

    private void serializeMap(Map<Long, ?> map, String fileName) throws HotelSerializationModuleException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
//                .setDateFormat("dd-MM-yyyy")
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
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
