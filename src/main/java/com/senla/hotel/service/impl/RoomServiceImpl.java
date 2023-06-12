package com.senla.hotel.service.impl;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.dao.impl.RoomDAOImpl;
import com.senla.hotel.entity.Room;
import com.senla.hotel.service.IRoomService;

public class RoomServiceImpl implements IRoomService {

    private RoomDAOImpl roomDAO;

    public void setRoomDAO(RoomDAOImpl roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public void doCheckIn(int roomId) {
        roomDAO.getRoom(roomId).setRoomAvailability(false);
    }

    @Override
    public void doCheckOut(int roomId) {
        roomDAO.getRoom(roomId).setRoomAvailability(true);
    }

    @Override
    public Room changeRoomServiceStatus(int roomId, ServiceStatus serviceStatus) {
        roomDAO.getRoom(roomId).setServiceStatus(serviceStatus);
        return roomDAO.getRoom(roomId);
    }

    @Override
    public Room changeRoomPrice(int roomId, double price) {
        roomDAO.getRoom(roomId).setPrice(price);
        return roomDAO.getRoom(roomId);
    }

    @Override
    public Room getRoom(int roomId) {
        return roomDAO.getRoom(roomId);
    }

    @Override
    public Room addRoom(Room room) {
        return roomDAO.addRoom(room);
    }
}
