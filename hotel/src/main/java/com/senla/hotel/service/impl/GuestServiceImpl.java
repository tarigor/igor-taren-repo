package com.senla.hotel.service.impl;

import com.senla.hotel.dto.GuestDto;
import com.senla.hotel.exception.HotelModuleException;
import com.senla.hotel.service.IGuestService;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GuestServiceImpl implements IGuestService {

    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository, PasswordEncoder passwordEncoder) {
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
                guestRepository.save(guestUpdate);
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

    public GuestDto registerGuest(GuestDto guestDto) throws HotelModuleException {
        if (emailExists(guestDto.getEmail())) {
            throw new HotelModuleException("There is an account with that email address: "
                    + guestDto.getEmail());
        }
        Guest guest = new Guest();
        guest.setFirstName(guestDto.getFirstName());
        guest.setLastName(guestDto.getLastName());
        guest.setPassword(passwordEncoder.encode(guestDto.getEmail()));
        guest.setEmail(guestDto.getEmail());
        guest.setRole(guestDto.getRole());
        Guest savedGuest = guestRepository.save(guest);
        return new GuestDto(savedGuest.getFirstName(), savedGuest.getLastName(), savedGuest.getEmail(), savedGuest.getRole());
    }

    private boolean emailExists(String email) {
        return guestRepository.findByEmail(email).isPresent();
    }
}
