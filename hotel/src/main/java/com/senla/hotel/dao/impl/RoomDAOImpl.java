package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class RoomDAOImpl implements IEntityDAO<Room> {

    private Map<Long, Room> rooms = new HashMap<>();

    public Map<Long, Room> getRooms() {
        return rooms;
    }

    @Override
    public Room update(Room room) {
        Room updatedRoom = getById(room.getId());
        updatedRoom.setPrice(room.getPrice());
        updatedRoom.setCapacity(room.getCapacity());
        updatedRoom.setStarsRating(room.getStarsRating());
        return updatedRoom;
    }

    @Override
    public Room getById(long roomId) {
        return rooms.get(roomId);
    }

    @Override
    public void save(Room room) {
        this.rooms.put(room.getId(), room);
    }

    @Override
    public List<Room> getAll() {
        return new ArrayList<>(rooms.values());
    }

    @Override
    public void saveAll(List<Room> rooms) {
        rooms.forEach(this::save);
    }


}
