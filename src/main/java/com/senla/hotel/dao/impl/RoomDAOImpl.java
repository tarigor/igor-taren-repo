package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.EntityDAO;
import com.senla.hotel.dao.IRoomDAO;
import com.senla.hotel.entity.Room;

import java.util.*;

public class RoomDAOImpl extends EntityDAO implements IRoomDAO {
    private static final RoomDAOImpl INSTANCE = new RoomDAOImpl();
    private static final Set<Long> idHolder = new HashSet<>();
    private final Map<Long, Room> rooms = new HashMap<>();

    public static RoomDAOImpl getInstance() {
        return INSTANCE;
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
        return Optional.ofNullable(rooms.get(roomId))
                .orElseThrow(() -> new NoSuchElementException("There is no such a room with id -> " + roomId));
    }

    @Override
    public Room save(Room room) {
        long id = generateId(idHolder);
        room.setId(id);
        rooms.put(id, room);
        return room;
    }

    @Override
    public List<Room> getAll() {
        return new ArrayList<>(rooms.values());
    }

    public void saveAll(List<Room> rooms) {
        rooms.forEach(this::save);
    }


}
