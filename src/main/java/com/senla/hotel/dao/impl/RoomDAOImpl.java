package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IRoomDAO;
import com.senla.hotel.entity.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class RoomDAOImpl implements IRoomDAO {
    private Map<Long, Room> rooms;

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
    public Room addRoom(Room room) {
        rooms.put(room.getRoomId(), room);
        return rooms.get(room.getRoomId());
    }

    @Override
    public List<Room> getRooms() {
        return new ArrayList<>(rooms.values());
    }

    public void setRooms(Map<Long, Room> rooms) {
        this.rooms = rooms;
    }
}
