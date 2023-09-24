package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.TakeDataFromPropertiesFile;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class GuestDAOImpl implements IEntityDAO<Guest> {
    private Map<Long, Guest> guests;
    @TakeDataFromPropertiesFile(entityName="Guest")
    public void setGuests(Map<Long, Guest> guests) {
        this.guests = guests;
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
