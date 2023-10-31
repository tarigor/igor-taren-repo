package com.senla.hotel.service.impl;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomSection;
import com.senla.hotel.constant.RoomStatus;
import com.senla.hotel.exception.HotelModuleException;
import com.senla.hotel.service.IRoomService;
import com.senla.hoteldb.entity.Room;
import com.senla.hoteldb.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements IRoomService {
    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Value("${ability-to-change-status-of-room}")
    private Boolean checkInCheckOutPermission;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void saveAll(List<Room> rooms) {
        roomRepository.saveAll(rooms);
    }

    @Override
    public void doCheckIn(long roomId) {
        if (checkInCheckOutPermission) {
            roomRepository
                    .findById(roomId).orElseThrow(() -> new NoSuchElementException("there is no such a room with id->" + roomId))
                    .setRoomStatus(RoomStatus.OCCUPIED.name());
            System.out.println("check-in performed for room -> " + roomId);
        } else {
            logger.error("It is not allowed to change the status of the room");
        }
    }

    @Override
    public void doCheckOut(long roomId) {
        if (checkInCheckOutPermission) {
            roomRepository
                    .findById(roomId).orElseThrow(() -> new NoSuchElementException("there is no such a room with id->" + roomId))
                    .setRoomStatus(RoomStatus.VACANT.name());
            System.out.println("check-out performed for room -> " + roomId);
        } else {
            logger.error("It is not allowed to change the status of the room");
        }
    }

    @Override
    public Room changeRoomPrice(long roomId, double price) {
        Room room = roomRepository
                .findById(roomId).orElseThrow(() -> new NoSuchElementException("there is no such a room with id->" + roomId));
        room.setPrice(price);
        return room;
    }

    @Override
    public Room getRoom(long roomId) {
        return roomRepository
                .findById(roomId).orElseThrow(() -> new NoSuchElementException("there is no such a room with id->" + roomId));
    }

    @Override
    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List<Room> findAllOrderedByPrice() {
        return roomRepository.findAll().stream()
                .sorted(Comparator.comparing(Room::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllOrderedByCapacity() {
        return roomRepository.findAll().stream()
                .sorted(Comparator.comparing(Room::getCapacity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllOrderedByStars() {
        return roomRepository.findAll().stream()
                .sorted(Comparator.comparing(Room::getStarsRating))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableOrderedByPrice() {
        return roomRepository.findAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name()))
                .sorted(Comparator.comparing(Room::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableOrderedByCapacity() {
        return roomRepository.findAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name()))
                .sorted(Comparator.comparing(Room::getCapacity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAvailableOrderedByStars() {
        return roomRepository.findAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name()))
                .sorted(Comparator.comparing(Room::getStarsRating))
                .collect(Collectors.toList());
    }

    @Override
    public int findNumberOfAvailableRooms() {
        return (int) roomRepository.findAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name()))
                .count();
    }

    @Override
    public double getRoomPrice(long roomId) {
        return roomRepository
                .findById(roomId)
                .orElseThrow(() -> new NoSuchElementException("there is no such a room with id->" + roomId))
                .getPrice();
    }

    //    Prices of services and rooms (sort by section(category), by price);
    @Override
    public List<Room> getAllOrdered(RoomSection roomSection, Ordering ordering) throws HotelModuleException {
        Comparator<Room> comparator = getComparatorForSection(roomSection, ordering);

        List<Room> rooms = roomRepository.findAll();

        if (comparator != null) {
            return rooms.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }
        logger.error("An ordering by section -> {} is not possible", roomSection);
        return Collections.emptyList();
    }

    private Comparator<Room> getComparatorForSection(RoomSection roomSection, Ordering ordering) throws HotelModuleException {
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
                throw new HotelModuleException("there is no such a section -> " + roomSection);
        }
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<Room> rooms) {
        for (Room room : rooms) {
            Optional<Room> roomOptional = roomRepository.findById(room.getId());
            if (roomOptional.isPresent()) {
                Room roomUpdate = roomOptional.get();
                roomUpdate.setRoomStatus(room.getRoomStatus());
                roomUpdate.setPrice(room.getPrice());
                roomUpdate.setCapacity(room.getCapacity());
                roomUpdate.setStarsRating(room.getStarsRating());
                roomRepository.save(roomUpdate);
            } else {
                roomRepository.save(room);
            }
        }
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }
}
