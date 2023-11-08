package com.senla.hotelweb.controller;

import com.senla.hotelsecurity.configuration.JwtTokenService;
import com.senla.hotelsecurity.configuration.JwtUserDetailsService;
import com.senla.hotelsecurity.dto.AuthenticationRequestDto;
import com.senla.hotelsecurity.dto.AuthenticationResponseDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class AuthenticateController {

    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public void setJwtTokenService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void setJwtUserDetailsService(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Autowired

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public AuthenticationResponseDto authenticate(@RequestBody @Valid final AuthenticationRequestDto authenticationRequestDto) {
        try {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(authenticationRequestDto.getLogin(), authenticationRequestDto.getPassword());
            authenticationManager.authenticate(authRequest);
        } catch (final BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequestDto.getLogin());
        final AuthenticationResponseDto authenticationResponse = new AuthenticationResponseDto();
        String token = jwtTokenService.generateToken(userDetails);
        authenticationResponse.setAccessToken(token);
        authenticationResponse.setExpirationTime(jwtTokenService.getValidTime(token));
        log.info("user token -> {} valid -> {}", authenticationResponse.getAccessToken(), authenticationResponse.getExpirationTime());
        return authenticationResponse;
    }
}
