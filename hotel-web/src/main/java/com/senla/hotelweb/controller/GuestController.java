package com.senla.hotelweb.controller;

import com.senla.hotel.dto.GuestServicesDto;
import com.senla.hotel.dto.searchcriteria.GuestServicesSearchCriteria;
import com.senla.hotel.enums.GuestServicesSection;
import com.senla.hotel.enums.Ordering;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/guest")
public class GuestController {
    private BookingServiceImpl bookingService;
    private GuestServicesServiceImpl guestServicesService;

    @Autowired
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    @Autowired
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    //9=The amount of payment for the room to be paid by the guest
    @GetMapping("/room/payment/byGuestId/{id}")
    public double getTotalPaymentByGuest(@PathVariable long id) {
        return bookingService.getTotalPaymentByGuest(id);
    }


    //11=View the list of guest services and their price (sorted by PRICE,DATE) in ASC(DESC) manner
    @GetMapping("/guestServices")
    public List<GuestServicesDto> getByGuestIdSorted(@Valid GuestServicesSearchCriteria guestServicesSearchCriteria) {
        Long guestId = guestServicesSearchCriteria.getGuestId();
        GuestServicesSection guestServicesSection = GuestServicesSection.valueOf(guestServicesSearchCriteria.getSortBy());
        Ordering ordering = Ordering.valueOf(guestServicesSearchCriteria.getSortOrder());
        return guestServicesService.getByGuestIdSorted(guestId, guestServicesSection, ordering);
    }
}
