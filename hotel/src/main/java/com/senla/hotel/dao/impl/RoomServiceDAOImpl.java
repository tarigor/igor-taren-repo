package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.repo.impl.RoomServiceRepositoryImpl;

import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomServiceDAOImpl implements IEntityDAO<RoomService> {
    private RoomServiceRepositoryImpl roomServiceRepository;

    @InjectValue(key = "RoomServiceRepositoryImpl")
    public void setRoomServiceRepository(RoomServiceRepositoryImpl roomServiceRepository) {
        this.roomServiceRepository = roomServiceRepository;
    }

    @Override
    public List<RoomService> getAll() {
        return roomServiceRepository.getAll();
    }

    @Override
    public void saveAll(List<RoomService> roomServices) {
        roomServiceRepository.saveAll(roomServices);
    }

    @Override
    public RoomService update(RoomService roomService) {
        return roomServiceRepository.update(roomService);
    }

    @Override
    public RoomService getById(long serviceId) {
        return roomServiceRepository.getById(serviceId);
    }

    @Override
    public void save(RoomService roomService) {
        roomServiceRepository.save(roomService);
    }
}
