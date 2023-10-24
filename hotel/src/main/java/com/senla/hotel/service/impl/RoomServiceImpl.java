package com.senla.hotel.service.impl;

import com.senla.betterthenspring.annotation.ConfigProperty;
import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.betterthenspring.annotation.InjectValue;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomSection;
import com.senla.hotel.constant.RoomStatus;
import com.senla.hotel.service.IRoomService;
import com.senla.hoteldb.dao.impl.RoomDao;
import com.senla.hoteldb.entity.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@CreateInstanceAndPutInContainer
public class RoomServiceImpl implements IRoomService {
    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    private Boolean checkInCheckOutPermission;
    private RoomDao roomDAO;

    @ConfigProperty(moduleName = "hotel", propertiesFileName = "settings", parameterName = "ability-to-change-status-of-room", type = Boolean.class)
    public void setCheckInCheckOutPermission(Boolean checkInCheckOutPermission) {
        this.checkInCheckOutPermission = checkInCheckOutPermission;
    }

    @InjectValue
    public void setRoomDAO(RoomDao roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public void saveAll(List<Room> rooms) {
        roomDAO.saveAll(rooms);
    }

    @Override
    public void doCheckIn(long roomId) {
        if (checkInCheckOutPermission) {
            roomDAO.getById(roomId).setRoomStatus(RoomStatus.OCCUPIED.name());
            System.out.println("check-in performed for room -> " + roomId);
        } else {
            logger.error("It is not allowed to change the status of the room");
        }
    }

    @Override
    public void doCheckOut(long roomId) {
        if (checkInCheckOutPermission) {
            roomDAO.getById(roomId).setRoomStatus(RoomStatus.VACANT.name());
            System.out.println("check-out performed for room -> " + roomId);
        } else {
            logger.error("It is not allowed to change the status of the room");
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
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name()))
                .sorted(Comparator.comparing(Room::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableOrderedByCapacity() {
        return roomDAO.getAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name()))
                .sorted(Comparator.comparing(Room::getCapacity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableOrderedByStars() {
        return roomDAO.getAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name()))
                .sorted(Comparator.comparing(Room::getStarsRating))
                .collect(Collectors.toList());
    }

    @Override
    public int findNumberOfAvailableRooms() {
        return (int) roomDAO.getAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name()))
                .count();
    }

    @Override
    public double getRoomPrice(long roomId) {
        return roomDAO.getById(roomId).getPrice();
    }

    //    Prices of services and rooms (sort by section(category), by price);
    @Override
    public List<Room> getAllOrdered(RoomSection roomSection, Ordering ordering) {
        Comparator<Room> comparator = getComparatorForSection(roomSection, ordering);

        List<Room> rooms = roomDAO.getAll();

        if (comparator != null) {
            return rooms.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            logger.error("An ordering by section -> {} is not possible", roomSection);
            throw new IllegalArgumentException("An ordering by section -> " + roomSection + " is not possible");
        }
    }

    private Comparator<Room> getComparatorForSection(RoomSection roomSection, Ordering ordering) {
        switch (roomSection) {
            case ID:
                return ordering == Ordering.ASC ? Comparator.comparing(Room::getId) : Comparator.comparing(Room::getId).reversed();
            case CAPACITY:
                return ordering == Ordering.ASC ? Comparator.comparing(Room::getCapacity) : Comparator.comparing(Room::getCapacity).reversed();
            case PRICE:
                return ordering == Ordering.ASC ? Comparator.comparing(Room::getPrice) : Comparator.comparing(Room::getPrice).reversed();
            case AVAILABILITY:
                return ordering == Ordering.ASC ? Comparator.comparing(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name())) :
                        Comparator.comparing(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name()), Comparator.reverseOrder());
            case RATING:
                return ordering == Ordering.ASC ? Comparator.comparing(Room::getStarsRating) : Comparator.comparing(Room::getStarsRating).reversed();
            default:
                logger.error("there is no such a section -> {}", roomSection);
                throw new NoSuchElementException("there is no such a section -> " + roomSection);
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
