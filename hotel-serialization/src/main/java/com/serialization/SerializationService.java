package com.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.entity.*;
import com.senla.hotel.repo.impl.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@CreateInstanceAndPutInContainer
public class SerializationService {
    private static final String RESOURCES_PATH = "\\hotel-serialization\\resources\\serialized";
    private BookingRepositoryImpl bookingRepository;
    private GuestRepositoryImpl guestRepository;
    private GuestServiceRepositoryImpl guestServiceRepository;
    private RoomRepositoryImpl roomRepository;
    private RoomServiceRepositoryImpl roomServiceRepository;

    @InjectValue(key = "BookingRepositoryImpl")
    public void setBookingRepository(BookingRepositoryImpl bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @InjectValue(key = "GuestRepositoryImpl")
    public void setGuestRepository(GuestRepositoryImpl guestRepository) {
        this.guestRepository = guestRepository;
    }

    @InjectValue(key = "GuestServiceRepositoryImpl")
    public void setGuestServiceRepository(GuestServiceRepositoryImpl guestServiceRepository) {
        this.guestServiceRepository = guestServiceRepository;
    }

    @InjectValue(key = "RoomRepositoryImpl")
    public void setRoomRepository(RoomRepositoryImpl roomRepository) {
        this.roomRepository = roomRepository;
    }

    @InjectValue(key = "RoomServiceRepositoryImpl")
    public void setRoomServiceRepository(RoomServiceRepositoryImpl roomServiceRepository) {
        this.roomServiceRepository = roomServiceRepository;
    }

    public void selectToSerialize(String mapName) {
        switch (mapName) {
            case "Booking": {
                serializeMap(bookingRepository.getAll().stream()
                        .collect(Collectors.groupingBy(Booking::getId)), "Booking");
                break;
            }
            case "Guest": {
                serializeMap(guestRepository.getAll().stream()
                        .collect(Collectors.groupingBy(Guest::getId)), "Guest");
                break;
            }
            case "GuestServices": {
                serializeMap(guestServiceRepository.getAll().stream()
                        .collect(Collectors.groupingBy(GuestServices::getId)), "GuestServices");
                break;
            }
            case "Room": {
                serializeMap(roomRepository.getAll().stream()
                        .collect(Collectors.groupingBy(Room::getId)), "Room");
                break;
            }
            case "RoomServices": {
                serializeMap(roomServiceRepository.getAll().stream()
                        .collect(Collectors.groupingBy(RoomService::getId)), "RoomServices");
                break;
            }
            default:
                throw new NoSuchElementException("There is no such an entity");
        }
    }

    private void serializeMap(Map<Long, ?> map, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + RESOURCES_PATH + "\\" + fileName + ".json")) {
            // Serialize the Map to JSON and write it to the file
            gson.toJson(map, writer);
            System.out.println("Serialization completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
