package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Room;
import com.senla.hotel.repo.impl.RoomRepositoryImpl;

import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomDAOImpl implements IEntityDAO<Room> {
    private RoomRepositoryImpl roomRepository;

    @InjectValue(key = "RoomRepositoryImpl")
    public void setRoomRepository(RoomRepositoryImpl roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room update(Room room) {
        return roomRepository.update(room);
    }

    @Override
    public Room getById(long roomId) {
        return roomRepository.getById(roomId);
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.getAll();
    }

    @Override
    public void saveAll(List<Room> rooms) {
        roomRepository.saveAll(rooms);
    }
}
