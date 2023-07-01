package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IRoomDAO;
import com.senla.hotel.entity.Room;

import java.util.*;

public class RoomDAOImpl implements IRoomDAO {
    private static final RoomDAOImpl INSTANCE = new RoomDAOImpl();
    private final Map<Long, Room> rooms = new HashMap<>();

    public static RoomDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Room updateRoom(Room room) {
        Room updatedRoom = getRoom(room.getId());
        updatedRoom.setPrice(room.getPrice());
        updatedRoom.setCapacity(room.getCapacity());
        updatedRoom.setStarsRating(room.getStarsRating());
        return updatedRoom;
    }

    @Override
    public Room getRoom(long roomId) {
        return Optional.ofNullable(rooms.get(roomId))
                .orElseThrow(() -> new NoSuchElementException("There is no such a room with id -> " + roomId));
    }

    @Override
    public Room save(Room room) {
        rooms.put(room.getId(), room);
        return room;
    }

    @Override
    public List<Room> getRooms() {
        return new ArrayList<>(rooms.values());
    }

    public void setRooms(List<Room> rooms) {
        rooms.forEach(this::save);
    }


}
