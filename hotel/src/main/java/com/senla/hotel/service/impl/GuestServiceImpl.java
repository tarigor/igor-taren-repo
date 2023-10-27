package com.senla.hotel.service.impl;

import com.senla.hotel.service.IGuestService;
import com.senla.hoteldb.dao.impl.GuestDao;
import com.senla.hoteldb.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestServiceImpl implements IGuestService {
    private GuestDao guestDAO;

    @Autowired
    public void setGuestDAO(GuestDao guestDAO) {
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
