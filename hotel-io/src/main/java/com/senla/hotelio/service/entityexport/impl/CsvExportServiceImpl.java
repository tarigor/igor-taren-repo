package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotelio.service.constant.EntityName;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CsvExportServiceImpl {
    private BookingEntityExportServiceImpl bookingEntityExportService;
    private GuestEntityExportServiceImpl guestEntityExportService;
    private GuestServicesEntityExportServiceImpl guestServicesEntityExportService;
    private RoomEntityExportServiceImpl roomEntityExportService;
    private RoomServiceEntityExportServiceImpl roomServiceEntityExportService;

    @Autowired
    public void setBookingEntityExportService(BookingEntityExportServiceImpl bookingEntityExportService) {
        this.bookingEntityExportService = bookingEntityExportService;
    }

    @Autowired
    public void setGuestEntityExportService(GuestEntityExportServiceImpl guestEntityExportService) {
        this.guestEntityExportService = guestEntityExportService;
    }

    @Autowired
    public void setGuestServicesEntityExportService(GuestServicesEntityExportServiceImpl guestServicesEntityExportService) {
        this.guestServicesEntityExportService = guestServicesEntityExportService;
    }

    @Autowired
    public void setRoomEntityExportService(RoomEntityExportServiceImpl roomEntityExportService) {
        this.roomEntityExportService = roomEntityExportService;
    }

    @Autowired
    public void setRoomServiceEntityExportService(RoomServiceEntityExportServiceImpl roomServiceEntityExportService) {
        this.roomServiceEntityExportService = roomServiceEntityExportService;
    }

    //Export the certain entity
    public void exportEntity(String entityNameString) {
        try {
            EntityName entityName = EntityName.valueOf(entityNameString.toUpperCase());
            switch (entityName) {
                case BOOKING -> bookingEntityExportService.exportEntity();
                case GUEST -> guestEntityExportService.exportEntity();
                case GUESTSERVICE -> guestServicesEntityExportService.exportEntity();
                case ROOM -> roomEntityExportService.exportEntity();
                case ROOMSERVICE -> roomServiceEntityExportService.exportEntity();
                default -> {
                    log.error("Wrong input! The selection must be in between 0-4. Try again");
                }
            }
        } catch (HotelIoModuleException e) {
            throw new IllegalArgumentException("");
        }
    }
}
