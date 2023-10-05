package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.Table;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.dao.service.DAOService;
import com.senla.hotel.entity.Guest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class GuestDAOImpl implements IEntityDAO<Guest> {
    private DAOService daoService;
    private Map<Long, Guest> guests = new HashMap<>();

    @InjectValue(key = "DAOService")
    public void setDaoService(DAOService daoService) {
        this.daoService = daoService;
    }

    public Map<Long, Guest> getGuests() {
        return guests;
    }

    public void setGuests(Map<Long, Guest> guests) {
        this.guests = guests;
    }

    @Override
    public List<Guest> getAll() {
        return daoService.getAll(Table.GUEST);
    }

    @Override
    public void saveAll(List<Guest> guests) {
        daoService.saveAll(guests, Table.GUEST);
        ;
    }

    @Override
    public Guest update(Guest guest) {
        return daoService.update(guest, Table.GUEST);
    }

    @Override
    public Guest getById(long guestId) {
        return daoService.getById(guestId, Table.GUEST);
    }

    @Override
    public void save(Guest guest) {
        daoService.save(guest, Table.GUEST);
    }


}
