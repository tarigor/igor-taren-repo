package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.EntityDAO;
import com.senla.hotel.dao.IRoomServiceDAO;
import com.senla.hotel.entity.RoomService;

import java.util.*;

public class RoomServiceDAOImpl extends EntityDAO implements IRoomServiceDAO {
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

    public void saveAll(List<RoomService> roomServices) {
        roomServices.forEach(this::save);
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
