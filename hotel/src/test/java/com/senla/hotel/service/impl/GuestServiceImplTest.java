package com.senla.hotel.service.impl;

import com.senla.hotel.dto.GuestDto;
import com.senla.hotel.exception.HotelModuleException;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.repository.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServiceImplTest {
    @Mock
    private GuestRepository guestRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private GuestServiceImpl guestService;
    private Guest guest1, guest2, guest3;
    private List<Guest> guests;

    @BeforeEach
    void setUp() {
        guest1 = new Guest(1L, "Ivan", "Ivanov", "ivnov@mail.com", "", "");
        guest2 = new Guest(2L, "Petr", "Petrov", "petrov@mail.com", "", "");
        guest3 = new Guest(2L, "Sidr", "Sidorov", "sidorov@mail.com", "", "");
        guests = List.of(guest1, guest2, guest3);
    }

    @Test
    void saveAll() {
        when(guestRepository.saveAll(guests)).thenReturn(guests);

        List<Guest> savedGuests = guestService.saveAll(guests);

        assertEquals(guests.size(), savedGuests.size());
        verify(guestRepository, times(1)).saveAll(guests);
    }

    @Test
    void updateAllAndSaveIfNotExist() {
        Guest existingGuest = guest1;
        Guest newGuest = new Guest(22L, "Alex", "Alex", "alex@mail.com", "", "");

        when(guestRepository.findById(existingGuest.getId())).thenReturn(Optional.of(existingGuest));
        when(guestRepository.findById(newGuest.getId())).thenReturn(Optional.empty());

        guestService.updateAllAndSaveIfNotExist(new ArrayList<>(List.of(existingGuest, newGuest)));

        verify(guestRepository, times(1)).save(existingGuest);
        verify(guestRepository, times(1)).save(newGuest);
    }

    @Test
    void getAll() {
        when(guestRepository.findAll()).thenReturn(guests);

        List<Guest> resultGuest = guestService.getAll();

        assertEquals(guests.size(), resultGuest.size());
        assertEquals(guests, resultGuest);
    }

    @Test
    void getById() {
        Guest guestToBeChecked = guest1;
        long guestIdToBeChecked = guest1.getId();

        when(guestRepository.findById(guestIdToBeChecked)).thenReturn(Optional.of(guestToBeChecked));

        Guest result = guestService.getById(guestIdToBeChecked);

        assertNotNull(result);
        assertEquals(guestToBeChecked, result);
        verify(guestRepository, times(1)).findById(guestIdToBeChecked);
    }

    @Test
    void registerGuest() throws HotelModuleException {
        GuestDto guestDto = new GuestDto("Petya", "Petrov", "petrov@mail.com", "password", "ROLE_USER");

        when(guestRepository.findByEmail(guestDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(guestDto.getPassword())).thenReturn("hashedPassword");
        when(guestRepository.save(any(Guest.class))).thenAnswer(invocation -> {
            Guest savedGuest = invocation.getArgument(0);
            savedGuest.setId(1L);
            return savedGuest;
        });

        GuestDto result = guestService.registerGuest(guestDto);

        verify(guestRepository).save(any(Guest.class));
        assertEquals("Petya", result.getFirstName());
        assertEquals("Petrov", result.getLastName());
        assertEquals("petrov@mail.com", result.getEmail());
        assertEquals("ROLE_USER", result.getRole());
    }
}