package com.senla.hotel.service.impl;

import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.betterthenspring.annotation.InjectValue;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomServiceSection;
import com.senla.hotel.service.IRoomServicesService;
import com.senla.hoteldb.dao.impl.RoomServiceDao;
import com.senla.hoteldb.entity.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CreateInstanceAndPutInContainer
public class RoomServicesServiceImpl implements IRoomServicesService {
    private static final Logger logger = LoggerFactory.getLogger(RoomServicesServiceImpl.class);
    private RoomServiceDao roomServiceDAO;

    @InjectValue
    public void setRoomServiceDAO(RoomServiceDao roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    @Override
    public void saveAll(List<RoomService> roomServices) {
        roomServiceDAO.saveAll(roomServices);
    }

    @Override
    public List<RoomService> getAllOrdered(RoomServiceSection roomServiceSection, Ordering ordering) {
        Comparator<RoomService> comparator;

        switch (roomServiceSection) {
            case ROOM_SERVICE:
                comparator = Comparator.comparing(RoomService::getServiceType);
                break;
            case PRICE:
                comparator = Comparator.comparing(RoomService::getPrice);
                break;
            default:
                logger.error("An ordering by section -> {} is not possible", roomServiceSection);
                throw new IllegalArgumentException("An ordering by section -> " + roomServiceSection + " is not possible");
        }

        if (ordering == Ordering.DESC) {
            comparator = comparator.reversed();
        }

        return roomServiceDAO.getAll().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<RoomService> roomServices) {
        for (RoomService roomService : roomServices) {
            if (roomServiceDAO.getById(roomService.getId()) != null) {
                roomServiceDAO.update(roomService);
            } else {
                roomServiceDAO.save(roomService);
            }
        }
    }

    @Override
    public List<RoomService> getAll() {
        return roomServiceDAO.getAll();
    }

    @Override
    public RoomService getById(Long id) {
        return roomServiceDAO.getById(id);
    }
}
