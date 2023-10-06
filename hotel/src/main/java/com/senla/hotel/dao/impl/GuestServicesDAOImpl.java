package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotel.repo.impl.GuestServiceRepositoryImpl;

import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestServicesDAOImpl implements IEntityDAO<GuestServices> {

    private GuestServiceRepositoryImpl guestServiceRepository;

    @InjectValue(key = "GuestServiceRepositoryImpl")
    public void setGuestServiceRepository(GuestServiceRepositoryImpl guestServiceRepository) {
        this.guestServiceRepository = guestServiceRepository;
    }

    @Override
    public List<GuestServices> getAll() {
        return guestServiceRepository.getAll();
    }

    @Override
    public void saveAll(List<GuestServices> guestServices) {
        guestServiceRepository.saveAll(guestServices);
    }

    @Override
    public GuestServices update(GuestServices guestServices) {
        return guestServiceRepository.update(guestServices);
    }

    @Override
    public GuestServices getById(long guestId) {
        return guestServiceRepository.getById(guestId);
    }

    @Override
    public void save(GuestServices guestServices) {
        guestServiceRepository.save(guestServices);
    }
}
