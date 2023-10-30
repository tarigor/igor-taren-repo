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
import com.senla.serialization.exception.HotelSerializationModuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SerializationService {
    public static final String EXTENSION_JSON = ".json";
    private static final Logger logger = LoggerFactory.getLogger(SerializationService.class);
    RoomServicesServiceImpl roomServicesService;
    @Value("${json.export.path}")
    private String jsonExportPath;
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

    public void selectToSerialize(String mapName) throws HotelSerializationModuleException {
        switch (mapName) {
            case "Booking":
                serializeMap(bookingService.getAll().stream()
                        .collect(Collectors.toMap(Booking::getId, booking -> booking)), "Booking");
                break;
            case "Guest":
                serializeMap(guestService.getAll().stream()
                        .collect(Collectors.toMap(Guest::getId, guest -> guest)), "Guest");
                break;
            case "GuestServices":
                serializeMap(guestServicesService.getAll().stream()
                        .collect(Collectors.toMap(GuestServices::getId, guestServices -> guestServices)), "GuestServices");
                break;
            case "Room":
                serializeMap(roomService.getAll().stream()
                        .collect(Collectors.toMap(Room::getId, room -> room)), "Room");
                break;
            case "RoomServices":
                serializeMap(roomServicesService.getAll().stream()
                        .collect(Collectors.toMap(RoomService::getId, roomService -> roomService)), "RoomServices");
                break;
            default:
                logger.error("There is no such an entity -> {}", mapName);
        }
    }

    private void serializeMap(Map<Long, ?> map, String fileName) throws HotelSerializationModuleException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(jsonExportPath + fileName + EXTENSION_JSON)) {
            gson.toJson(map, writer);
            logger.info("Serialization completed successfully");
        } catch (IOException e) {
            logger.error("an error occurred during an IO operation -> {}", e.getMessage());
            throw new HotelSerializationModuleException(e);
        }
    }
}
