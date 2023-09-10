package com.senla.hotel.service.impl;

import com.senla.hotel.dao.impl.GuestDAOImpl;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.service.CommonService;
import com.senla.hotel.service.IGuestService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuestServiceImpl extends CommonService implements IGuestService {
    private static final GuestServiceImpl INSTANCE = new GuestServiceImpl();
    private static final Set<Long> idHolder = new HashSet<>();
    private final GuestDAOImpl guestDAO = GuestDAOImpl.getInstance();

    public static GuestServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveAll(List<Guest> guests) {
        for (Guest guest : guests) {
            setId(guest);
        }
        guestDAO.saveAll(guests);
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<Guest> guests) {
        for (Guest guest : guests) {
            if (guestDAO.getById(guest.getId()) != null) {
                guestDAO.update(guest);
            } else {
                setId(guest);
                guestDAO.save(guest);
            }
        }
    }

    @Override
    public List<Guest> getAll() {
        return guestDAO.getAll();
    }

    private void setId(Guest guest) {
        if (guest.getId() == 0) {
            guest.setId(generateId(idHolder));
        }
    }
}
