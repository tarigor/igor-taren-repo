package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IGuestDAO;
import com.senla.hotel.entity.Guest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GuestDAOImpl implements IGuestDAO {
    private Map<Long, Guest> guests;

    @Override
    public List<Guest> getGuests() {
        return new ArrayList<>(guests.values());
    }

    @Override
    public Guest getGuestById(long guestId) {
        return guests.get(guestId);
    }

    public void setGuests(Map<Long, Guest> rooms) {
        this.guests = guests;
    }
}
