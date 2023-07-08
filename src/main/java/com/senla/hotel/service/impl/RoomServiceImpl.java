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
    private static final RoomServiceImpl INSTANCE = new RoomServiceImpl();
    private IRoomServiceDAO roomServiceDAO;

    private IRoomDAO roomDAO;
    private IGuestServicesDAO guestServicesDAO;

    public static RoomServiceImpl getInstance() {
        return INSTANCE;
    }

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
        roomDAO.getById(roomId).setAvailable(false);
    }

    @Override
    public void doCheckOut(long roomId) {
        roomDAO.getById(roomId).setAvailable(true);
    }

    @Override
    public Room changeRoomService(long roomId, long serviceTypeId) {
        roomDAO.getById(roomId).setRoomServiceId(serviceTypeId);
        return roomDAO.getById(roomId);
    }

    @Override
    public Room changeRoomPrice(long roomId, double price) {
        roomDAO.getById(roomId).setPrice(price);
        return roomDAO.getById(roomId);
    }

    @Override
    public Room getRoom(long roomId) {
        return roomDAO.getById(roomId);
    }

    @Override
    public Room addRoom(Room room) {
        return roomDAO.save(room);
    }

    @Override
    public List<Room> findAllOrderedByPrice() {
        return roomDAO.getAll().stream()
                .sorted(Comparator.comparing(Room::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllOrderedByCapacity() {
        return roomDAO.getAll().stream()
                .sorted(Comparator.comparing(Room::getCapacity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllOrderedByStars() {
        return roomDAO.getAll().stream()
                .sorted(Comparator.comparing(Room::getStarsRating))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableOrderedByPrice() {
        return roomDAO.getAll().stream()
                .filter(Room::isAvailable)
                .sorted(Comparator.comparing(Room::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableOrderedByCapacity() {
        return roomDAO.getAll().stream()
                .filter(Room::isAvailable)
                .sorted(Comparator.comparing(Room::getCapacity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableOrderedByStars() {
        return roomDAO.getAll().stream()
                .filter(Room::isAvailable)
                .sorted(Comparator.comparing(Room::getStarsRating))
                .collect(Collectors.toList());
    }

    @Override
    public int findNumberOfAvailableRooms() {
        return (int) roomDAO.getAll().stream()
                .filter(Room::isAvailable)
                .count();
    }

    @Override
    public double getRoomPrice(long roomId) {
        return roomDAO.getById(roomId).getPrice();
    }

    //    Prices of services and rooms (sort by section(category), by price);
    @Override
    public List<Room> getRoomsOrderedBySection(int sectionNumber) {
        switch (sectionNumber) {
            case 1:
                return roomDAO.getAll().stream()
                        .sorted(Comparator.comparing(Room::getId))
                        .collect(Collectors.toList());
            case 2:
                return roomDAO.getAll().stream()
                        .sorted(Comparator.comparing(Room::getCapacity))
                        .collect(Collectors.toList());
            case 3:
                return roomDAO.getAll().stream()
                        .sorted(Comparator.comparing(Room::getPrice))
                        .collect(Collectors.toList());
            case 4:
                return roomDAO.getAll().stream()
                        .sorted(Comparator.comparing(Room::isAvailable))
                        .collect(Collectors.toList());
            case 5:
                return roomDAO.getAll().stream()
                        .sorted(Comparator.comparing(Room::getRoomServiceId))
                        .collect(Collectors.toList());
            case 6:
                return roomDAO.getAll().stream()
                        .sorted(Comparator.comparing(Room::getStarsRating))
                        .collect(Collectors.toList());
            default:
                throw new IndexOutOfBoundsException("There is no such a section with id->" + sectionNumber);
        }
    }

    @Override
    public List<Room> getRoomsOrderedByPrice() {
        return roomDAO.getAll().stream()
                .sorted(Comparator.comparing(Room::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomService> getRoomServicesOrderedByCategory() {
        return roomServiceDAO.getAll().stream()
                .sorted(Comparator.comparing(RoomService::getServiceType))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomService> getRoomServicesOrderedByPrice() {
        return roomServiceDAO.getAll().stream()
                .sorted(Comparator.comparing(RoomService::getPrice))
                .collect(Collectors.toList());
    }
}
