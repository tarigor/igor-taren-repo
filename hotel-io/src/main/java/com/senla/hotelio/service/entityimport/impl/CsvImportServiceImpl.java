package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hotelio.service.enums.EntityName;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CsvImportServiceImpl {
    private BookingServiceImpl bookingService;
    private GuestServiceImpl guestService;
    private GuestServicesServiceImpl guestServicesService;
    private RoomServiceImpl roomService;
    private RoomServicesServiceImpl roomServicesService;
    private BookingEntityImportServiceImpl bookingEntityImportService;
    private GuestEntityImportServiceImpl guestEntityImportService;
    private GuestServicesEntityImportServiceImpl guestServicesEntityImportService;
    private RoomEntityImportServiceImpl roomEntityImportService;
    private RoomServiceEntityImportServiceImpl roomServiceEntityImportService;

    @Autowired
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @Autowired
    public void setGuestService(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @Autowired
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    @Autowired
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setRoomServicesService(RoomServicesServiceImpl roomServicesService) {
        this.roomServicesService = roomServicesService;
    }

    @Autowired
    public void setBookingEntityImportService(BookingEntityImportServiceImpl bookingEntityImportService) {
        this.bookingEntityImportService = bookingEntityImportService;
    }

    @Autowired
    public void setGuestEntityImportService(GuestEntityImportServiceImpl guestEntityImportService) {
        this.guestEntityImportService = guestEntityImportService;
    }

    @Autowired
    public void setGuestServicesEntityImportService(GuestServicesEntityImportServiceImpl guestServicesEntityImportService) {
        this.guestServicesEntityImportService = guestServicesEntityImportService;
    }

    @Autowired
    public void setRoomEntityImportService(RoomEntityImportServiceImpl roomEntityImportService) {
        this.roomEntityImportService = roomEntityImportService;
    }

    @Autowired
    public void setRoomServiceEntityImportService(RoomServiceEntityImportServiceImpl roomServiceEntityImportService) {
        this.roomServiceEntityImportService = roomServiceEntityImportService;
    }

    //Import the certain entity from the CSV file
    public void importEntity(String entityNameString) {
        try {
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
        } catch (HotelIoModuleException e) {
            throw new IllegalArgumentException("");
        }
    }
}
