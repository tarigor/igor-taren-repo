package com.senla.hotel.service.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.service.IGuestService;
import com.senla.hoteldb.dao.impl.GuestDAO;
import com.senla.hoteldb.entity.Guest;

import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestServiceImpl implements IGuestService {
    private GuestDAO guestDAO;

    @InjectValue
    public void setGuestDAO(GuestDAO guestDAO) {
        this.guestDAO = guestDAO;
    }

    @Override
    public void saveAll(List<Guest> guests) {
        guestDAO.saveAll(guests);
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<Guest> guests) {
        for (Guest guest : guests) {
            if (guestDAO.getById(guest.getId()) != null) {
                guestDAO.update(guest);
            } else {
                guestDAO.save(guest);
            }
        }
    }

    @Override
    public List<Guest> getAll() {
        return guestDAO.getAll();
    }

    @Override
    public Guest getById(Long id) {
        return guestDAO.getById(id);
    }
}
