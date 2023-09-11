package com.senla.hotel.service;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomServiceSection;
import com.senla.hotel.entity.RoomService;

import java.util.ArrayList;
import java.util.List;

public interface IRoomServicesService {
    void saveAll(List<RoomService> roomServices);

    List<RoomService> getAllOrdered(RoomServiceSection roomServiceSection, Ordering ordering);

    void updateAllAndSaveIfNotExist(ArrayList<RoomService> roomServices);

    List<RoomService> getAll();
}
