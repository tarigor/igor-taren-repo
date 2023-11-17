package com.senla.hotel.service.impl;

import com.senla.hotel.enums.Ordering;
import com.senla.hotel.enums.RoomServiceSection;
import com.senla.hotel.service.IRoomServicesService;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hoteldb.repository.RoomServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoomServicesServiceImpl implements IRoomServicesService {

    private final RoomServiceRepository roomServiceRepository;

    @Autowired
    public RoomServicesServiceImpl(RoomServiceRepository roomServiceRepository) {
        this.roomServiceRepository = roomServiceRepository;
    }

    @Override
    public List<RoomService> saveAll(List<RoomService> roomServices) {
        return roomServiceRepository.saveAll(roomServices);
    }

    @Override
    public List<RoomService> getAllOrdered(RoomServiceSection roomServiceSection, Ordering ordering) {
        Comparator<RoomService> comparator = null;

        switch (roomServiceSection) {
            case ROOM_SERVICE -> comparator = Comparator.comparing(RoomService::getServiceType);
            case PRICE -> comparator = Comparator.comparing(RoomService::getPrice);
            default -> log.error("An ordering by section -> {} is not possible", roomServiceSection);
        }

        if (ordering == Ordering.DESC) {
            comparator = comparator.reversed();
        }

        return roomServiceRepository.findAll().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<RoomService> roomServices) {
        for (RoomService roomService : roomServices) {
            Optional<RoomService> roomServiceOptional = roomServiceRepository.findById(roomService.getId());
            if (roomServiceOptional.isPresent()) {
                RoomService roomServiceUpdate = roomServiceOptional.get();
                roomServiceUpdate.setServiceType(roomService.getServiceType());
                roomServiceUpdate.setPrice(roomService.getPrice());
                roomServiceRepository.save(roomServiceUpdate);
            } else {
                roomServiceRepository.save(roomService);
            }
        }
    }

    @Override
    public List<RoomService> getAll() {
        return roomServiceRepository.findAll();
    }

    @Override
    public RoomService getById(Long id) {
        return roomServiceRepository.findById(id).orElseThrow(() -> new NoSuchElementException("there is no such a room with id->" + id));
    }
}
