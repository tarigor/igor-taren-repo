package com.senla.hotel.service;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomServiceSection;
import com.senla.hotel.entity.RoomService;

import java.util.List;

public interface IRoomServicesService {
    List<RoomService> getAllOrdered(RoomServiceSection roomServiceSection, Ordering ordering);
}
