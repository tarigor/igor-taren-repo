package com.senla.hotel.service.impl;

import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.dao.IGuestServicesDAO;
import com.senla.hotel.dao.IRoomServiceDAO;
import com.senla.hotel.dto.GuestServicesDTO;
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
    public List<GuestServicesDTO> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering) {
        switch (guestServicesSection) {
            case PRICE:
                return ordering == Ordering.ASC ?
                        guestServicesDAO.getById(guestId)
                                .getServicesOrdered().entrySet().stream()
                                .map(e -> new GuestServicesDTO(e.getKey(), roomServiceDAO.getById(e.getValue())))
                                .sorted(Comparator.comparingDouble((GuestServicesDTO g) -> g.getRoomService().getPrice()))
                                .collect(Collectors.toList()) :
                        guestServicesDAO.getById(guestId)
                                .getServicesOrdered().entrySet().stream()
                                .map(e -> new GuestServicesDTO(e.getKey(), roomServiceDAO.getById(e.getValue())))
                                .sorted(Comparator.comparingDouble((GuestServicesDTO g) -> g.getRoomService().getPrice()).reversed())
                                .collect(Collectors.toList());
            case DATE:
                return ordering == Ordering.ASC ?
                        guestServicesDAO.getById(guestId).getServicesOrdered().entrySet().stream()
                                .map(e -> new GuestServicesDTO(e.getKey(), roomServiceDAO.getById(e.getValue())))
                                .sorted(Comparator.comparing(GuestServicesDTO::getDate))
                                .collect(Collectors.toList()) :
                        guestServicesDAO.getById(guestId).getServicesOrdered().entrySet().stream()
                                .map(e -> new GuestServicesDTO(e.getKey(), roomServiceDAO.getById(e.getValue())))
                                .sorted(Comparator.comparing(GuestServicesDTO::getDate).reversed())
                                .collect(Collectors.toList());
            default:
                throw new IndexOutOfBoundsException("An ordering by section ->" + guestServicesSection + "is not possible");
        }
    }
}
