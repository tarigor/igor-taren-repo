package com.senla.hotelweb.controller;

import com.senla.hotel.dto.GuestServicesDto;
import com.senla.hotel.dto.searchcriteria.GuestServicesSearchCriteria;
import com.senla.hotel.enums.GuestServicesSection;
import com.senla.hotel.enums.Ordering;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guestServices")
public class GuestServicesController {

    private GuestServicesServiceImpl guestServicesService;

    @Autowired
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    //11=View the list of guest services and their price (sorted by PRICE,DATE) in ASC(DESC) manner
    @GetMapping
    public List<GuestServicesDto> getByGuestIdSorted(@Valid GuestServicesSearchCriteria guestServicesSearchCriteria) {
        Long guestId = guestServicesSearchCriteria.getGuestId();
        GuestServicesSection guestServicesSection = GuestServicesSection.valueOf(guestServicesSearchCriteria.getSortBy());
        Ordering ordering = Ordering.valueOf(guestServicesSearchCriteria.getSortOrder());
        return guestServicesService.getByGuestIdSorted(guestId, guestServicesSection, ordering);
    }
}


