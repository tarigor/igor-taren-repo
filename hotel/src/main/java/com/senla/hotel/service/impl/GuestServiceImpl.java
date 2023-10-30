package com.senla.hotel.service.impl;

import com.senla.hotel.service.IGuestService;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GuestServiceImpl implements IGuestService {
    @Autowired
    private GuestRepository guestRepository;

    @Override
    public void saveAll(List<Guest> guests) {
        guestRepository.saveAll(guests);
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<Guest> guests) {
        for (Guest guest : guests) {
            Optional<Guest> guestOptional = guestRepository.findById(guest.getId());
            if (guestOptional.isPresent()) {
                Guest guestUpdate = guestOptional.get();
                guestUpdate.setFirstName(guest.getFirstName());
                guestUpdate.setLastName(guest.getLastName());
            } else {
                guestRepository.save(guest);
            }
        }
    }

    @Override
    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    @Override
    public Guest getById(Long id) {
        return guestRepository.findById(id).orElseThrow(() -> new NoSuchElementException("there is no such a guest with id->" + id));
    }
}
