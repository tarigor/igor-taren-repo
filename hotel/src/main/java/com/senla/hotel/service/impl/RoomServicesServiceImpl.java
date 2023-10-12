package com.senla.hotel.service.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomServiceSection;
import com.senla.hotel.dao.impl.RoomServiceDAOImpl;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.IRoomServicesService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CreateInstanceAndPutInContainer
public class RoomServicesServiceImpl implements IRoomServicesService {
    private RoomServiceDAOImpl roomServiceDAO;

    @InjectValue
    public void setRoomServiceDAO(RoomServiceDAOImpl roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    @Override
    public void saveAll(List<RoomService> roomServices) {
        roomServiceDAO.saveAll(roomServices);
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
}
