package com.senla.hotel.service.impl;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomSection;
import com.senla.hotel.constant.RoomStatus;
import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.service.IRoomService;
import com.senla.hotel.util.EntityDtoMapper;
import com.senla.hoteldb.entity.Room;
import com.senla.hoteldb.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private EntityDtoMapper entityDtoMapper;

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
    public RoomDto getRoom(long roomId) {
        return
                entityDtoMapper.convertFromEntityToDto(
                        roomRepository
                                .findById(roomId)
                                .orElseThrow(() -> new NoSuchElementException("there is no such a room with id->" + roomId)), RoomDto.class);
    }

    @Override
    public RoomDto addRoom(RoomDto room) {
        return entityDtoMapper.convertFromEntityToDto(roomRepository.save(entityDtoMapper.convertFromDtoToEntity(room, Room.class)), RoomDto.class);
    }

    @Override
    public List<RoomDto> getSortedRooms(String sortBy) {
        RoomSection roomSection;
        try {
            roomSection = RoomSection.valueOf(sortBy);
        } catch (IllegalArgumentException e) {
            logger.error("wrong input parameters -> {} : {}", sortBy, e.getMessage());
            throw new IllegalArgumentException("wrong input parameters -> " + sortBy);
        }
        return roomRepository.findAll().stream()
                .sorted(getComparator(roomSection, Ordering.ASC))
                .map(room -> entityDtoMapper.convertFromEntityToDto(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getAvailableSortedRooms(String sortBy, String sortOrder) {
        RoomSection roomSection;
        Ordering ordering;
        try {
            roomSection = RoomSection.valueOf(sortBy);
            ordering = Ordering.valueOf(sortOrder);
        } catch (IllegalArgumentException e) {
            logger.error("wrong input parameters -> {} : {}", sortBy, e.getMessage());
            throw new IllegalArgumentException("wrong input parameters -> " + sortBy);
        }
        return roomRepository.findAll().stream()
                .filter(room -> room.getRoomStatus().equals(RoomStatus.VACANT.name()))
                .sorted(getComparator(roomSection, ordering))
                .map(room -> entityDtoMapper.convertFromEntityToDto(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalAvailableRooms() {
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

    // 12=Prices of services and rooms (sort by section(category), by price);
    @Override
    public List<RoomDto> getAllOrdered(RoomSection roomSection, Ordering ordering) {
        return roomRepository.findAll().stream()
                .sorted(getComparator(roomSection, ordering))
                .map(room -> entityDtoMapper.convertFromEntityToDto(room, RoomDto.class))
                .collect(Collectors.toList());
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

    private Comparator<Room> getComparator(RoomSection roomSection, Ordering ordering) {
        Comparator<Room> comparator = null;
        switch (roomSection) {
            case ID -> comparator = Comparator.comparing(Room::getId);
            case AVAILABILITY -> comparator = Comparator.comparing(Room::getRoomStatus);
            case PRICE -> comparator = Comparator.comparing(Room::getPrice);
            case CAPACITY -> comparator = Comparator.comparing(Room::getCapacity);
            case RATING -> comparator = Comparator.comparing(Room::getStarsRating);
            default -> logger.error("An ordering by section -> {} is not possible", roomSection);
        }
        if (ordering == Ordering.DESC) {
            comparator = comparator.reversed();
        }
        return comparator;
    }
}
