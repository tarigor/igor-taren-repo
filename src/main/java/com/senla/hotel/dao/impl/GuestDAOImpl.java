package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IGuestDAO;
import com.senla.hotel.entity.Guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuestDAOImpl implements IGuestDAO {
    private static final GuestDAOImpl INSTANCE = new GuestDAOImpl();
    private final Map<Long, Guest> guests = new HashMap<>();

    public static GuestDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Guest> getGuests() {
        return new ArrayList<>(guests.values());
    }

    public void setGuests(List<Guest> guests) {
        guests.forEach(this::save);
    }

    @Override
    public Guest getGuestById(long guestId) {
        return guests.get(guestId);
    }

    @Override
    public void save(Guest guest) {
        this.guests.put(guest.getId(), guest);
    }


}
