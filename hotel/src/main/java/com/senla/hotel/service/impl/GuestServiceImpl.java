package com.senla.hotel.service.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.impl.GuestDAOImpl;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.service.IGuestService;

import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestServiceImpl implements IGuestService {
    private GuestDAOImpl guestDAO;

    @InjectValue
    public void setGuestDAO(GuestDAOImpl guestDAO) {
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
