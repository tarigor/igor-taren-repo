package com.senla.hotel.service.impl;

import com.senla.hotel.dao.IGuestServicesDAO;
import com.senla.hotel.dao.IRoomServiceDAO;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotel.service.IGuestServicesService;

import java.util.List;

public class GuestServicesServiceImpl implements IGuestServicesService {
    private IGuestServicesDAO guestServicesDAO;

    private IRoomServiceDAO roomServiceDAO;

    public void setGuestServicesDAO(IGuestServicesDAO guestServicesDAO) {
        this.guestServicesDAO = guestServicesDAO;
    }

    public void setRoomServiceDAO(IRoomServiceDAO roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    @Override
    public GuestServices getGuestServicesByPrice(long guestId) {
        return guestServicesDAO.getGuestServicesByGuestId(guestId);
    }

    @Override
    public GuestServices getGuestServicesByDate(long guestId) {
        return null;
    }

    @Override
    public List<GuestServices> getServicePriceByCategoryByPrice(long serviceStatusId) {
        return null;
    }
}
