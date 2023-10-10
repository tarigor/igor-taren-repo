package com.senla.hotel.service.impl;

import com.senla.container.ConfigProperty;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomSection;
import com.senla.hotel.constant.RoomStatus;
import com.senla.hotel.dao.impl.RoomDAOImpl;
import com.senla.hotel.entity.Room;
import com.senla.hotel.service.IRoomService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CreateInstanceAndPutInContainer
public class RoomServiceImpl implements IRoomService {
    private Boolean checkInCheckOutPermission;
    private RoomDAOImpl roomDAO;

    @ConfigProperty(moduleName = "hotel", propertiesFileName = "settings", parameterName = "ability-to-change-status-of-room", type = Boolean.class)
    public void setCheckInCheckOutPermission(Boolean checkInCheckOutPermission) {
        this.checkInCheckOutPermission = checkInCheckOutPermission;
    }

    @InjectValue(key = "RoomDAOImpl")
    public void setRoomDAO(RoomDAOImpl roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public void saveAll(List<Room> rooms) {
        roomDAO.saveAll(rooms);
    }

    @Override
    public void doCheckIn(long roomId) {
        if (checkInCheckOutPermission) {
            roomDAO.getById(roomId).setRoomStatus(RoomStatus.OCCUPIED);
            System.out.println("check-in performed for room -> " + roomId);
        } else {
            System.out.println("It is not allowed to change the status of the room");
        }
    }

    @Override
    public void doCheckOut(long roomId) {
        if (checkInCheckOutPermission) {
            roomDAO.getById(roomId).setRoomStatus(RoomStatus.VACANT);
            System.out.println("check-out performed for room -> " + roomId);
        } else {
            System.out.println("It is not allowed to change the status of the room");
        }
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
    public void addRoom(Room room) {
        roomDAO.save(room);
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
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT))
                .sorted(Comparator.comparing(Room::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableOrderedByCapacity() {
        return roomDAO.getAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT))
                .sorted(Comparator.comparing(Room::getCapacity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableOrderedByStars() {
        return roomDAO.getAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT))
                .sorted(Comparator.comparing(Room::getStarsRating))
                .collect(Collectors.toList());
    }

    @Override
    public int findNumberOfAvailableRooms() {
        return (int) roomDAO.getAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT))
                .count();
    }

    @Override
    public double getRoomPrice(long roomId) {
        return roomDAO.getById(roomId).getPrice();
    }

    //    Prices of services and rooms (sort by section(category), by price);
    @Override
    public List<Room> getAllOrdered(RoomSection roomSection, Ordering ordering) {
        switch (roomSection) {
            case ID:
                return ordering == Ordering.ASC ?
                        roomDAO.getAll().stream()
                                .sorted(Comparator.comparing(Room::getId))
                                .collect(Collectors.toList()) :
                        roomDAO.getAll().stream()
                                .sorted(Comparator.comparing(Room::getId).reversed())
                                .collect(Collectors.toList());

            case CAPACITY:
                return ordering == Ordering.ASC ?
                        roomDAO.getAll().stream()
                                .sorted(Comparator.comparing(Room::getCapacity))
                                .collect(Collectors.toList()) :
                        roomDAO.getAll().stream()
                                .sorted(Comparator.comparing(Room::getCapacity).reversed())
                                .collect(Collectors.toList());
            case PRICE:
                return ordering == Ordering.ASC ?
                        roomDAO.getAll().stream()
                                .sorted(Comparator.comparing(Room::getPrice))
                                .collect(Collectors.toList()) :
                        roomDAO.getAll().stream()
                                .sorted(Comparator.comparing(Room::getPrice).reversed())
                                .collect(Collectors.toList());
            case AVAILABILITY:
                return ordering == Ordering.ASC ?
                        roomDAO.getAll().stream()
                                .sorted(Comparator.comparing(room -> room.getRoomStatus().equals(RoomStatus.VACANT)))
                                .collect(Collectors.toList()) :
                        roomDAO.getAll().stream()
                                .sorted(Comparator.comparing(room -> room.getRoomStatus().equals(RoomStatus.VACANT), Comparator.reverseOrder()))
                                .collect(Collectors.toList());
            case RATING:
                return ordering == Ordering.ASC ?
                        roomDAO.getAll().stream()
                                .sorted(Comparator.comparing(Room::getStarsRating))
                                .collect(Collectors.toList()) :
                        roomDAO.getAll().stream()
                                .sorted(Comparator.comparing(Room::getStarsRating).reversed())
                                .collect(Collectors.toList());
            default:
                throw new IndexOutOfBoundsException("An ordering by section ->" + roomSection + "is not possible");
        }
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<Room> rooms) {
        for (Room room : rooms) {
            if (roomDAO.getById(room.getId()) != null) {
                roomDAO.update(room);
            } else {
                roomDAO.save(room);
            }
        }
    }

    @Override
    public List<Room> getAll() {
        return roomDAO.getAll();
    }
}
