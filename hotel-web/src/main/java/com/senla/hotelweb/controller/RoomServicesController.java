package com.senla.hotelweb.controller;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomServiceSection;
import com.senla.hotel.dto.searchcriteria.RoomServiceSearchCriteria;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roomServices")
public class RoomServicesController {
    @Autowired
    private RoomServicesServiceImpl roomServicesService;

    //13=Room services (ordered by ROME_SERVICES,PRICE) in ASC(DESC) manner
    @GetMapping
    public List<RoomService> getAllOrdered(@Valid RoomServiceSearchCriteria roomServiceSearchCriteria) {
        RoomServiceSection roomServiceSection = RoomServiceSection.valueOf(roomServiceSearchCriteria.getSortBy());
        Ordering ordering = Ordering.valueOf(roomServiceSearchCriteria.getSortOrder());
        return roomServicesService.getAllOrdered(roomServiceSection, ordering);
    }
}
