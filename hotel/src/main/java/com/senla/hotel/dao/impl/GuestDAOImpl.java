package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Guest;

import java.util.*;

public class GuestDAOImpl implements IEntityDAO<Guest> {
    private static final GuestDAOImpl INSTANCE = new GuestDAOImpl();
    private static final Set<Long> idHolder = new HashSet<>();
    private final Map<Long, Guest> guests = new HashMap<>();

    public static GuestDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Guest> getAll() {
        return new ArrayList<>(guests.values());
    }

    @Override
    public void saveAll(List<Guest> guests) {
        guests.forEach(this::save);
    }

    @Override
    public Guest update(Guest guest) {
        Guest updatedGuest = getById(guest.getId());
        updatedGuest.setFirstName(guest.getFirstName());
        updatedGuest.setLastName(guest.getLastName());
        return updatedGuest;
    }

    @Override
    public Guest getById(long guestId) {
        return guests.get(guestId);
    }

    @Override
    public void save(Guest guest) {
        this.guests.put(guest.getId(), guest);
    }


}
