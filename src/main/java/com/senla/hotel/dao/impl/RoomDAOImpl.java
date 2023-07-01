package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IRoomDAO;
import com.senla.hotel.entity.Room;

import java.util.*;

public class RoomDAOImpl implements IRoomDAO {
    private static final RoomDAOImpl INSTANCE = new RoomDAOImpl();
    private Map<Long, Room> rooms = new HashMap<>();

    public static RoomDAOImpl getInstance() {
        return INSTANCE;
    }

    public void setRooms(List<Room> rooms) {
        rooms.forEach(this::save);
    }

    @Override
    public Room updateRoom(Room room) {
        getRoom(room.getRoomId()).setPrice(room.getPrice());
        getRoom(room.getRoomId()).setCapacity(room.getCapacity());
        getRoom(room.getRoomId()).setStarsRating(room.getStarsRating());
        return getRoom(room.getRoomId());
    }

    @Override
    public Room getRoom(long roomId) {
        for (Map.Entry<Long, Room> entry : rooms.entrySet()) {
            if (entry.getKey().equals(roomId)) {
                return entry.getValue();
            }
        }
        throw new NoSuchElementException("There is no sucha room with id -> " + roomId);
    }

    @Override
    public Room save(Room room) {
        rooms.put(room.getRoomId(), room);
        return rooms.get(room.getRoomId());
    }

    @Override
    public List<Room> getRooms() {
        return new ArrayList<>(rooms.values());
    }


}
