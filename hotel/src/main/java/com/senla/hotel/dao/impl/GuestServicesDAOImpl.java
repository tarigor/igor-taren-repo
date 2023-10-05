package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.Table;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.dao.service.DAOService;
import com.senla.hotel.entity.GuestServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class GuestServicesDAOImpl implements IEntityDAO<GuestServices> {
    private DAOService daoService;
    @InjectValue(key = "DAOService")
    public void setDaoService(DAOService daoService) {
        this.daoService = daoService;
    }
    private Map<Long, GuestServices> guestServices = new HashMap<>();

    public Map<Long, GuestServices> getGuestServices() {
        return guestServices;
    }

    public void setGuestServices(Map<Long, GuestServices> guestServices) {
        this.guestServices = guestServices;
    }

    @Override
    public List<GuestServices> getAll() {
        return daoService.getAll(Table.GUEST_SERVICE);
    }

    @Override
    public void saveAll(List<GuestServices> guestServices) {
        daoService.saveAll(guestServices, Table.GUEST_SERVICE);
    }

    @Override
    public GuestServices update(GuestServices guestServices) {
        return daoService.update(guestServices, Table.GUEST_SERVICE);
    }

    @Override
    public GuestServices getById(long guestId) {
        return daoService.getById(guestId, Table.GUEST_SERVICE);
    }

    @Override
    public void save(GuestServices guestServices) {
        daoService.save(guestServices, Table.GUEST_SERVICE);
    }
}
