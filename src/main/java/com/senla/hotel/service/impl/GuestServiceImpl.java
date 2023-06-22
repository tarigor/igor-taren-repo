package com.senla.hotel.service.impl;

import com.senla.hotel.dao.IGuestDAO;
import com.senla.hotel.service.IGuestService;

public class GuestServiceImpl implements IGuestService {
    private IGuestDAO guestDAO;

    public void setGuestDAO(IGuestDAO guestDAO) {
        this.guestDAO = guestDAO;
    }

    @Override
    public int findCountOfAllGuests() {
        return 0;
    }
}
