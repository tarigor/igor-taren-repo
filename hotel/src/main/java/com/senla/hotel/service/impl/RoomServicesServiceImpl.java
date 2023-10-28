package com.senla.hotel.service.impl;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomServiceSection;
import com.senla.hotel.service.IRoomServicesService;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hoteldb.repository.RoomServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServicesServiceImpl implements IRoomServicesService {
    private static final Logger logger = LoggerFactory.getLogger(RoomServicesServiceImpl.class);
    @Autowired
    private RoomServiceRepository roomServiceRepository;

    @Override
    public void saveAll(List<RoomService> roomServices) {
        roomServiceRepository.saveAll(roomServices);
    }

    @Override
    public List<RoomService> getAllOrdered(RoomServiceSection roomServiceSection, Ordering ordering) {
        Comparator<RoomService> comparator = null;

        switch (roomServiceSection) {
            case ROOM_SERVICE:
                comparator = Comparator.comparing(RoomService::getServiceType);
                break;
            case PRICE:
                comparator = Comparator.comparing(RoomService::getPrice);
                break;
            default:
                logger.error("An ordering by section -> {} is not possible", roomServiceSection);
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
