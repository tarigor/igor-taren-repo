package com.senla.hotel.service.impl;

import com.senla.hotel.dao.IGuestServicesDAO;
import com.senla.hotel.dao.IRoomServiceDAO;
import com.senla.hotel.dto.GuestServicesDTO;
import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.IGuestServicesService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GuestServicesServiceImpl implements IGuestServicesService {
    private static final GuestServicesServiceImpl INSTANCE = new GuestServicesServiceImpl();
    private IGuestServicesDAO guestServicesDAO;
    private IRoomServiceDAO roomServiceDAO;

    public static GuestServicesServiceImpl getInstance() {
        return INSTANCE;
    }

    public void setGuestServicesDAO(IGuestServicesDAO guestServicesDAO) {
        this.guestServicesDAO = guestServicesDAO;
    }

    public void setRoomServiceDAO(IRoomServiceDAO roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }


    //    View the list of guest services and their price (sort by price, by date);
    @Override
    public List<GuestServicesDTO> getGuestServicesByPrice(long guestId) {
        return guestServicesDAO.getGuestServicesByGuestId(guestId).getServicesOrdered().entrySet().stream()
                .map(e -> new GuestServicesDTO(e.getKey(), roomServiceDAO.getRoomServiceById(e.getValue())))
                .sorted(Comparator.comparingDouble((GuestServicesDTO g) -> g.getRoomService().getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestServicesDTO> getGuestServicesByDate(long guestId) {
        return guestServicesDAO.getGuestServicesByGuestId(guestId).getServicesOrdered().entrySet().stream()
                .map(e -> new GuestServicesDTO(e.getKey(), roomServiceDAO.getRoomServiceById(e.getValue())))
                .sorted(Comparator.comparing(GuestServicesDTO::getDate))
                .collect(Collectors.toList());
    }

    //    Prices of services and rooms (sort by section(category), by price);

    @Override
    public List<Room> getRoomsBySection() {
        return null;
    }

    @Override
    public List<Room> getRoomsByPrice() {
        return null;
    }

    @Override
    public List<RoomService> getRoomServicesByCategory() {
        return null;
    }

    @Override
    public List<RoomService> getRoomServicesByPrice() {
        return null;
    }
}
