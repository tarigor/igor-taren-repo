package com.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@CreateInstanceAndPutInContainer
public class SerializationService {
    private static final Logger logger = LoggerFactory.getLogger(SerializationService.class);
    private static final String RESOURCES_PATH = "\\hotel-serialization\\src\\main\\resources\\serialized";
    RoomServicesServiceImpl roomServicesService;
    private BookingServiceImpl bookingService;
    private GuestServiceImpl guestService;
    private GuestServicesServiceImpl guestServicesService;
    private RoomServiceImpl roomService;

    @InjectValue
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @InjectValue
    public void setGuestService(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @InjectValue
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    @InjectValue
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @InjectValue
    public void setRoomServicesService(RoomServicesServiceImpl roomServicesService) {
        this.roomServicesService = roomServicesService;
    }

    public void selectToSerialize(String mapName) {
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
                throw new NoSuchElementException("There is no such an entity");
        }
    }

    private void serializeMap(Map<Long, ?> map, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + RESOURCES_PATH + "\\" + fileName + ".json")) {
            // Serialize the Map to JSON and write it to the file
            gson.toJson(map, writer);
            logger.info("Serialization completed successfully");
        } catch (IOException e) {
            logger.error("an error occurred during an IO operation->" + e.getMessage());
            e.printStackTrace();
        }
    }
}
