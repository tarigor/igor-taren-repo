package com.senla.serialization.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DeserializationService {

    @Value("${json.import.path}")
    private String jsonImportPath;
    private BookingServiceImpl bookingService;
    private GuestServiceImpl guestService;
    private GuestServicesServiceImpl guestServicesService;
    private RoomServiceImpl roomService;
    private RoomServicesServiceImpl roomServicesService;

    private static String readFileToString(String filePath) throws HotelSerializationModuleException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error("an error occurred during an IO operation -> {}", e.getMessage());
            throw new HotelSerializationModuleException(e);
        }
        return content.toString();
    }

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

    public void deserialize(String fileName) {
        try {
            EntityName entityName = EntityName.valueOf(fileName.toUpperCase());
            switch (entityName) {
                case BOOKING -> {
                    //Booking
                    HashMap<Long, Booking> bookings = (HashMap<Long, Booking>) deserializeToMap(Booking.class, "Booking");
                    bookingService.updateAllAndSaveIfNotExist(new ArrayList<>(bookings.values()));
                }
                case GUEST -> {
                    //Guest
                    HashMap<Long, Guest> guests = (HashMap<Long, Guest>) deserializeToMap(Guest.class, "Guest");
                    guestService.updateAllAndSaveIfNotExist(new ArrayList<>(guests.values()));
                }
                case GUESTSERVICE -> {
                    //GuestService
                    HashMap<Long, GuestServices> guestServices = (HashMap<Long, GuestServices>) deserializeToMap(GuestServices.class, "GuestServices");
                    guestServicesService.updateAllAndSaveIfNotExist(new ArrayList<>(guestServices.values()));
                }
                case ROOM -> {
                    //Room
                    HashMap<Long, Room> rooms = (HashMap<Long, Room>) deserializeToMap(Room.class, "Room");
                    roomService.updateAllAndSaveIfNotExist(new ArrayList<>(rooms.values()));
                }
                case ROOMSERVICE -> {
                    //RoomService
                    HashMap<Long, RoomService> roomServices = (HashMap<Long, RoomService>) deserializeToMap(RoomService.class, "RoomServices");
                    roomServicesService.updateAllAndSaveIfNotExist(new ArrayList<>(roomServices.values()));
                }
                default -> log.error("Wrong input! The selection must be in between 0-4. Try again");
            }
        } catch (HotelSerializationModuleException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> Map<Long, T> deserializeToMap(Class<T> type, String fileName) throws HotelSerializationModuleException {
        String fileContent = readFileToString(jsonImportPath + fileName.toLowerCase() + ".json");
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new CustomDateDeserializer());
        objectMapper.registerModule(module);
        try {
            return objectMapper.readValue(
                    fileContent,
                    objectMapper.getTypeFactory().constructMapType(HashMap.class, Long.class, type)
            );
        } catch (JsonProcessingException e) {
            log.error("an error occurred during an JSON operation -> {}", e.getMessage());
            throw new HotelSerializationModuleException(e);
        }
    }
}
