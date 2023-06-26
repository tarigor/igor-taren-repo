package com.senla.hotel.service.impl;

import com.senla.hotel.dao.IGuestServicesDAO;
import com.senla.hotel.dao.IRoomDAO;
import com.senla.hotel.dao.IRoomServiceDAO;
import com.senla.hotel.dao.impl.RoomDAOImpl;
import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.IRoomService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements IRoomService {
    private IRoomServiceDAO roomServiceDAO;

    private IRoomDAO roomDAO;
    private IGuestServicesDAO guestServicesDAO;

    public void setRoomDAO(RoomDAOImpl roomDAO) {
        this.roomDAO = roomDAO;
    }

    public void setRoomServiceDAO(IRoomServiceDAO roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    public void setGuestServicesDAO(IGuestServicesDAO guestServicesDAO) {
        this.guestServicesDAO = guestServicesDAO;
    }

    @Override
    public void doCheckIn(long roomId) {
        roomDAO.getRoom(roomId).setRoomAvailability(false);
    }

    @Override
    public void doCheckOut(long roomId) {
        roomDAO.getRoom(roomId).setRoomAvailability(true);
    }

    @Override
    public Room changeRoomServiceStatus(long roomId, long serviceStatusId) {
        roomDAO.getRoom(roomId).setRoomServiceId(serviceStatusId);
        return roomDAO.getRoom(roomId);
    }

    @Override
    public Room changeRoomPrice(long roomId, double price) {
        roomDAO.getRoom(roomId).setPrice(price);
        return roomDAO.getRoom(roomId);
    }

    @Override
    public Room getRoom(long roomId) {
        return roomDAO.getRoom(roomId);
    }

    @Override
    public Room addRoom(Room room) {
        return roomDAO.addRoom(room);
    }

    @Override
    public List<Room> findAllByPrice() {
        return roomDAO.getRooms().stream()
                .sorted(Comparator.comparing(Room::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllByCapacity() {
        return roomDAO.getRooms().stream()
                .sorted(Comparator.comparing(Room::getCapacity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllByStars() {
        return roomDAO.getRooms().stream()
                .sorted(Comparator.comparing(Room::getStarsRating))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableByPrice() {
        return roomDAO.getRooms().stream()
                .filter(Room::isRoomAvailability)
                .sorted(Comparator.comparing(Room::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableByCapacity() {
        return roomDAO.getRooms().stream()
                .filter(Room::isRoomAvailability)
                .sorted(Comparator.comparing(Room::getCapacity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableByStars() {
        return roomDAO.getRooms().stream()
                .filter(Room::isRoomAvailability)
                .sorted(Comparator.comparing(Room::getStarsRating))
                .collect(Collectors.toList());
    }

    @Override
    public int findNumberOfAvailableRooms() {
        return (int) roomDAO.getRooms().stream()
                .filter(Room::isRoomAvailability)
                .count();
    }

    @Override
    public double getRoomPrice(long roomId) {
        return roomDAO.getRoom(roomId).getPrice();
    }
}
