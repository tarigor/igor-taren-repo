package com.senla.hotelsecurity.service;

import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.repository.GuestRepository;
import com.senla.hotelsecurity.entity.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.senla.hotelsecurity.enums.Role.ROLE_GUEST;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private GuestRepository guestRepository;

    @Autowired
    public void setGuestRepository(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        System.out.println("in loadUserByUsername");
        final Guest guest = guestRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User " + username + " not found"));
        final List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority(ROLE_GUEST.name()));
        return new JwtUserDetails(guest.getId(), username, guest.getPassword(), roles);
    }
}
