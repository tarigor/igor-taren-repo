package com.senla.hotel.service.impl;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomServiceSection;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.IRoomServicesService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoomServicesServiceImpl implements IRoomServicesService {
    private static final RoomServicesServiceImpl INSTANCE = new RoomServicesServiceImpl();
    private IEntityDAO<RoomService> roomServiceDAO;

    public static RoomServicesServiceImpl getInstance() {
        return INSTANCE;
    }

    public void setRoomServiceDAO(IEntityDAO<RoomService> roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    @Override
    public List<RoomService> getAllOrdered(RoomServiceSection roomServiceSection, Ordering ordering) {
        switch (roomServiceSection) {
            case ROOM_SERVICE:
                return ordering == Ordering.ASC ?
                        roomServiceDAO.getAll().stream()
                                .sorted(Comparator.comparing(RoomService::getServiceType))
                                .collect(Collectors.toList()) :
                        roomServiceDAO.getAll().stream()
                                .sorted(Comparator.comparing(RoomService::getServiceType).reversed())
                                .collect(Collectors.toList());
            case PRICE:
                return ordering == Ordering.ASC ?
                        roomServiceDAO.getAll().stream()
                                .sorted(Comparator.comparing(RoomService::getPrice))
                                .collect(Collectors.toList()) :
                        roomServiceDAO.getAll().stream()
                                .sorted(Comparator.comparing(RoomService::getPrice).reversed())
                                .collect(Collectors.toList());
            default:
                throw new IndexOutOfBoundsException("An ordering by section ->" + roomServiceSection + "is not possible");
        }
    }
}
