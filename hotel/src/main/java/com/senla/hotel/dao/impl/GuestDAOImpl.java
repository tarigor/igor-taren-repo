package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.repo.impl.GuestRepositoryImpl;

import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestDAOImpl implements IEntityDAO<Guest> {
    private GuestRepositoryImpl guestRepository;

    @InjectValue(key = "GuestRepositoryImpl")
    public void setGuestRepository(GuestRepositoryImpl guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public List<Guest> getAll() {
        return guestRepository.getAll();
    }

    @Override
    public void saveAll(List<Guest> guests) {
        guestRepository.saveAll(guests);
    }

    @Override
    public Guest update(Guest guest) {
        return guestRepository.update(guest);
    }

    @Override
    public Guest getById(long guestId) {
        return guestRepository.getById(guestId);
    }

    @Override
    public void save(Guest guest) {
        guestRepository.save(guest);
    }
}
