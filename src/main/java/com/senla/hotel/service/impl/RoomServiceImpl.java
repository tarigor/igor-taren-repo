package com.senla.hotel.service.impl;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.dao.impl.RoomDAOImpl;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.entity.GuestDates;
import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.Service;
import com.senla.hotel.service.IRoomService;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements IRoomService {

    private RoomDAOImpl roomDAO;

    public void setRoomDAO(RoomDAOImpl roomDAO) {
        this.roomDAO = roomDAO;
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
    public Room changeRoomServiceStatus(long roomId, ServiceStatus serviceStatus) {
        roomDAO.getRoom(roomId).setServiceStatus(serviceStatus);
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
        return roomDAO.getRooms().stream().sorted(Comparator.comparing(Room::getPrice)).collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllByCapacity() {
        return roomDAO.getRooms().stream().sorted(Comparator.comparing(Room::getCapacity)).collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllByStars() {
        return roomDAO.getRooms().stream().sorted(Comparator.comparing(Room::getStarsRating)).collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableByPrice() {
        return roomDAO.getRooms().stream()
                .filter(Room::isRoomAvailability)
                .sorted(Comparator.comparing(Room::getPrice)).collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableByCapacity() {
        return roomDAO.getRooms().stream()
                .filter(Room::isRoomAvailability)
                .sorted(Comparator.comparing(Room::getCapacity)).collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableByStars() {
        return roomDAO.getRooms().stream()
                .filter(Room::isRoomAvailability)
                .sorted(Comparator.comparing(Room::getStarsRating)).collect(Collectors.toList());
    }

    @Override
    public List<Guest> findAllSortByAlphAndCheckout() {
        return null;
    }

    @Override
    public int findNumberOfAvailableRooms() {
        return (int) roomDAO.getRooms().stream()
                .filter(Room::isRoomAvailability)
                .count();
    }

    @Override
    public int findCountOfAllGuests() {
        return 0;
    }

    @Override
    public List<Room> findAvailableRoomsByDate(Date date) {
        return null;
    }

    @Override
    public double getTotalPaymentByGuest(long guestId) {
        return 0;
    }

    @Override
    public List<GuestDates> findLastGuestOfRoomAndDates(int countOfGuests, long roomId) {
        return null;
    }

    @Override
    public List<Service> getGuestServicesInfo(long guestId, boolean sortedByPrice, boolean sortedByDate) {
        return null;
    }

    @Override
    public List<Service> getServicePriceByCategoryByPrice(ServiceStatus serviceStatus) {
        return null;
    }
}
