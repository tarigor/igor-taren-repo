package com.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.impl.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

@CreateInstanceAndPutInContainer
public class SerializationService {
    private static final String RESOURCES_PATH = "\\hotel-serialization\\resources\\serialized";
    private BookingDAOImpl bookingDAO;
    private GuestDAOImpl guestDAO;
    private GuestServicesDAOImpl guestServicesDAO;
    private RoomDAOImpl roomDAO;
    private RoomServiceDAOImpl roomServiceDAO;

    @InjectValue(key = "BookingDAOImpl")
    public void setBookingDAO(BookingDAOImpl bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @InjectValue(key = "GuestDAOImpl")
    public void setGuestDAO(GuestDAOImpl guestDAO) {
        this.guestDAO = guestDAO;
    }

    @InjectValue(key = "GuestServicesDAOImpl")
    public void setGuestServicesDAO(GuestServicesDAOImpl guestServicesDAO) {
        this.guestServicesDAO = guestServicesDAO;
    }

    @InjectValue(key = "RoomDAOImpl")
    public void setRoomDAO(RoomDAOImpl roomDAO) {
        this.roomDAO = roomDAO;
    }

    @InjectValue(key = "RoomServiceDAOImpl")
    public void setRoomServiceDAO(RoomServiceDAOImpl roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    public void selectToSerialize(String mapName) {
        switch (mapName) {
            case "Booking": {
                serializeMap(bookingDAO.getBookings(), "Booking");
                break;
            }
            case "Guest": {
                serializeMap(guestDAO.getGuests(), "Guest");
                break;
            }
            case "GuestServices": {
                serializeMap(guestServicesDAO.getGuestServices(), "GuestServices");
                break;
            }
            case "Room": {
                serializeMap(roomDAO.getRooms(), "Room");
                break;
            }
            case "RoomServices": {
                serializeMap(roomServiceDAO.getRoomServices(), "RoomServices");
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
