package com.senla.hotel.service.impl;

import com.senla.hotel.dao.impl.GuestDAOImpl;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.service.IGuestService;

import java.util.List;

public class GuestServiceImpl implements IGuestService {
    private static final GuestServiceImpl INSTANCE = new GuestServiceImpl();
    private final GuestDAOImpl guestDAO = GuestDAOImpl.getInstance();

    public static GuestServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveAll(List<Guest> guests) {
        guestDAO.saveAll(guests);
    }
}
