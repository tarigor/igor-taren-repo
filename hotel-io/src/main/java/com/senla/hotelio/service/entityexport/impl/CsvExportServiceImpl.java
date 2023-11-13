package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotelio.service.enums.EntityName;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CsvExportServiceImpl {
    private final BookingEntityExportServiceImpl bookingEntityExportService;
    private final GuestEntityExportServiceImpl guestEntityExportService;
    private final GuestServicesEntityExportServiceImpl guestServicesEntityExportService;
    private final RoomEntityExportServiceImpl roomEntityExportService;
    private final RoomServiceEntityExportServiceImpl roomServiceEntityExportService;

    @Autowired
    public CsvExportServiceImpl(BookingEntityExportServiceImpl bookingEntityExportService,
                                GuestEntityExportServiceImpl guestEntityExportService,
                                GuestServicesEntityExportServiceImpl guestServicesEntityExportService,
                                RoomEntityExportServiceImpl roomEntityExportService,
                                RoomServiceEntityExportServiceImpl roomServiceEntityExportService) {
        this.bookingEntityExportService = bookingEntityExportService;
        this.guestEntityExportService = guestEntityExportService;
        this.guestServicesEntityExportService = guestServicesEntityExportService;
        this.roomEntityExportService = roomEntityExportService;
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
