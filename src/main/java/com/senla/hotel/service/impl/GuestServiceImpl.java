package com.senla.hotel.service.impl;

import com.senla.hotel.dao.IGuestDAO;
import com.senla.hotel.service.IGuestService;

public class GuestServiceImpl implements IGuestService {
    private static final GuestServiceImpl INSTANCE = new GuestServiceImpl();
    private IGuestDAO guestDAO;

    public static GuestServiceImpl getInstance() {
        return INSTANCE;
    }

    public void setGuestDAO(IGuestDAO guestDAO) {
        this.guestDAO = guestDAO;
    }

}
