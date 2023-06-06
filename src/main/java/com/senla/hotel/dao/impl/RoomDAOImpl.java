package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IRoomDAO;
import com.senla.hotel.entity.Room;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class RoomDAOImpl implements IRoomDAO {
    private HashMap<Integer, Room> rooms;

    public void setRooms(HashMap<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room updateRoom(Room room) {
        Room updatingRoom = getRoom(room.getRoomId());
        updatingRoom.setServiceStatus(room.getServiceStatus());
        updatingRoom.setPrice(room.getPrice());
        updatingRoom.setRoomAvailability(room.isRoomAvailability());
        return updatingRoom;
    }

    @Override
    public Room getRoom(int roomId) {
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            if (entry.getKey().equals(roomId)) {
                return entry.getValue();
            }
        }
        throw new NoSuchElementException("There is no sucha room with id -> " + roomId);
    }

    @Override
    public int addRoom(Room room) {
        getRoom(room.getRoomId());
        rooms.put(room.getRoomId(), room);
        return room.getRoomId();
    }
}
