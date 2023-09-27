package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.GuestServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class GuestServicesDAOImpl implements IEntityDAO<GuestServices> {
    private Map<Long, GuestServices> guestServices = new HashMap<>();
    @Override
    public List<GuestServices> getAll() {
        return new ArrayList<>(guestServices.values());
    }

    @Override
    public void saveAll(List<GuestServices> guestServices) {
        guestServices.forEach(this::save);
    }

    @Override
    public GuestServices update(GuestServices guestServices) {
        GuestServices updatedGuestServices = getById(guestServices.getGuestId());
        updatedGuestServices.setGuestId(guestServices.getGuestId());
        updatedGuestServices.setServicesOrdered(guestServices.getServicesOrdered());
        return updatedGuestServices;
    }

    @Override
    public GuestServices getById(long guestId) {
        return guestServices.get(guestId);
    }

    @Override
    public void save(GuestServices guestServices) {
        this.guestServices.put(guestServices.getId(), guestServices);
    }
}
