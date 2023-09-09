package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.RoomService;

import java.util.*;

public class RoomServiceDAOImpl implements IEntityDAO<RoomService> {
    private static final RoomServiceDAOImpl INSTANCE = new RoomServiceDAOImpl();
    private static final Set<Long> idHolder = new HashSet<>();
    private final Map<Long, RoomService> roomServices = new HashMap<>();

    public static RoomServiceDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<RoomService> getAll() {
        return new ArrayList<>(roomServices.values());
    }

    @Override
    public void saveAll(List<RoomService> roomServices) {
        roomServices.forEach(this::save);
    }

    @Override
    public RoomService update(RoomService roomService) {
        RoomService updatedRoomService = getById(roomService.getId());
        updatedRoomService.setServiceType(roomService.getServiceType());
        updatedRoomService.setPrice(roomService.getPrice());
        return null;
    }

    @Override
    public RoomService getById(long serviceId) {
        return roomServices.get(serviceId);
    }

    @Override
    public void save(RoomService roomService) {
        long id = generateId(idHolder);
        roomService.setId(id);
        roomServices.put(id, roomService);
    }
}
