package com.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.entity.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

@CreateInstanceAndPutInContainer
public class SerializationService {
    private static final String RESOURCES_PATH = "\\hotel-serialization\\resources";
    private Map<Long, Booking> bookings;
    private Map<Long, Guest> guests;
    private Map<Long, GuestServices> guestServices;
    private Map<Long, Room> rooms;
    private Map<Long, RoomService> roomServices;

    public void setBookings(Map<Long, Booking> bookings) {
        this.bookings = bookings;
    }

    public void setGuests(Map<Long, Guest> guests) {
        this.guests = guests;
    }

    public void setGuestServices(Map<Long, GuestServices> guestServices) {
        this.guestServices = guestServices;
    }

    public void setRooms(Map<Long, Room> rooms) {
        this.rooms = rooms;
    }

    public void setRoomServices(Map<Long, RoomService> roomServices) {
        this.roomServices = roomServices;
    }

    public void selectToSerialize(String mapName) {
        switch (mapName) {
            case "Booking": {
                serializeMap(bookings, "Booking");
                break;
            }
            case "Guest": {
                serializeMap(guests, "Guest");
                break;
            }
            case "GuestServices": {
                serializeMap(guestServices, "GuestServices");
                break;
            }
            case "Room": {
                serializeMap(rooms, "Room");
                break;
            }
            case "RoomServices": {
                serializeMap(roomServices, "RoomServices");
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
