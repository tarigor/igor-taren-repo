package com.senla.adssecurity.service;

import com.senla.adsdatabase.entity.User;
import com.senla.adsdatabase.repository.UserRepository;
import com.senla.adssecurity.entity.JwtUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final User user = userRepository.findUserByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User " + username + " not found"));
        final List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole()));
        return new JwtUserDetails(user.getId(), username, user.getPassword(), roles);
    }
}
