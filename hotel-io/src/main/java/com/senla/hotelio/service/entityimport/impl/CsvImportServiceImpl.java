package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hotelio.service.enums.EntityName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CsvImportServiceImpl {
    private final BookingServiceImpl bookingService;
    private final GuestServiceImpl guestService;
    private final GuestServicesServiceImpl guestServicesService;
    private final RoomServiceImpl roomService;
    private final RoomServicesServiceImpl roomServicesService;
    private final BookingEntityImportServiceImpl bookingEntityImportService;
    private final GuestEntityImportServiceImpl guestEntityImportService;
    private final GuestServicesEntityImportServiceImpl guestServicesEntityImportService;
    private final RoomEntityImportServiceImpl roomEntityImportService;
    private final RoomServiceEntityImportServiceImpl roomServiceEntityImportService;

    @Autowired
    public CsvImportServiceImpl(BookingServiceImpl bookingService,
                                GuestServiceImpl guestService,
                                GuestServicesServiceImpl guestServicesService,
                                RoomServiceImpl roomService,
                                RoomServicesServiceImpl roomServicesService,
                                BookingEntityImportServiceImpl bookingEntityImportService,
                                GuestEntityImportServiceImpl guestEntityImportService,
                                GuestServicesEntityImportServiceImpl guestServicesEntityImportService,
                                RoomEntityImportServiceImpl roomEntityImportService,
                                RoomServiceEntityImportServiceImpl roomServiceEntityImportService) {
        this.bookingService = bookingService;
        this.guestService = guestService;
        this.guestServicesService = guestServicesService;
        this.roomService = roomService;
        this.roomServicesService = roomServicesService;
        this.bookingEntityImportService = bookingEntityImportService;
        this.guestEntityImportService = guestEntityImportService;
        this.guestServicesEntityImportService = guestServicesEntityImportService;
        this.roomEntityImportService = roomEntityImportService;
        this.roomServiceEntityImportService = roomServiceEntityImportService;
    }

    //Import the certain entity from the CSV file
    public void importEntity(String entityNameString) {
        EntityName entityName = EntityName.valueOf(entityNameString.toUpperCase());
        switch (entityName) {
            case BOOKING -> bookingService.updateAllAndSaveIfNotExist(bookingEntityImportService.importEntities());
            case GUEST -> guestService.updateAllAndSaveIfNotExist(guestEntityImportService.importEntities());
            case GUESTSERVICE ->
                    guestServicesService.updateAllAndSaveIfNotExist(guestServicesEntityImportService.importEntities());
            case ROOM -> roomService.updateAllAndSaveIfNotExist(roomEntityImportService.importEntities());
            case ROOMSERVICE ->
                    roomServicesService.updateAllAndSaveIfNotExist(roomServiceEntityImportService.importEntities());
            default -> log.error("Wrong input! The selection must be in between 0-4. Try again");
        }
    }
}
