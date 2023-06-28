package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IGuestServicesDAO;
import com.senla.hotel.entity.GuestServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GuestServicesDAOImpl implements IGuestServicesDAO {
    private static final GuestServicesDAOImpl INSTANCE = new GuestServicesDAOImpl();
    private Map<Long, GuestServices> guestServices;

    public static GuestServicesDAOImpl getInstance() {
        return INSTANCE;
    }
    public void setGuestServices(Map<Long, GuestServices> guestServices) {
        this.guestServices = guestServices;
    }

    @Override
    public List<GuestServices> getGuestServices() {
        return new ArrayList<>(guestServices.values());
    }

    @Override
    public GuestServices getGuestServicesByGuestId(long guestId) {
        return guestServices.get(guestId);
    }
}
