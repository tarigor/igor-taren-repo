package com.senla.serialization.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hoteldb.entity.Room;
import com.senla.hoteldb.entity.RoomService;
import com.senla.serialization.enums.EntityName;
import com.senla.serialization.exception.HotelSerializationModuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class DeserializationService {

    @Value("${json.import.path}")
    private String jsonImportPath;

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

    public HashMap<Long, ?> deserialize(String fileName) {
        try {
            EntityName entityName = EntityName.valueOf(fileName.toUpperCase());
            switch (entityName) {
                case BOOKING -> {
                    //Booking
                    return (HashMap<Long, Booking>) deserializeToMap(Booking.class, "Booking");
                }
                case GUEST -> {
                    //Guest
                    return (HashMap<Long, Guest>) deserializeToMap(Guest.class, "Guest");
                }
                case GUESTSERVICE -> {
                    //GuestService
                    return (HashMap<Long, GuestServices>) deserializeToMap(GuestServices.class, "GuestServices");
                }
                case ROOM -> {
                    //Room
                    return (HashMap<Long, Room>) deserializeToMap(Room.class, "Room");
                }
                case ROOMSERVICE -> {
                    //RoomService
                    return (HashMap<Long, RoomService>) deserializeToMap(RoomService.class, "RoomServices");
                }
                default -> log.error("Wrong input! The selection must be in between 0-4. Try again");
            }
        } catch (HotelSerializationModuleException e) {
            throw new RuntimeException(e);
        }
        throw new NoSuchElementException("No such entity defined");
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
