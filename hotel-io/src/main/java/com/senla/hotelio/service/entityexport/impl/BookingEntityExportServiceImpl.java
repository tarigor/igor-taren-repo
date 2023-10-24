package com.senla.hotelio.service.entityexport.impl;

import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.betterthenspring.annotation.InjectValue;
import com.senla.hotel.dto.entityexport.BookingExport;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hoteldb.entity.Booking;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;

import java.util.List;
import java.util.stream.Collectors;


@CreateInstanceAndPutInContainer
public class BookingEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "Booking";

    private BookingServiceImpl bookingService;

    @InjectValue
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void exportEntity() throws HotelIoModuleException {
        List<Booking> bookings = bookingService.getAll();
        storeEntityToCsv(
                ENTITY_FILENAME,
                bookings.stream()
                        .map(b -> new BookingExport(b.getId(), b.getGuest().getId(), b.getRoom().getId(), b.getCheckInDate(), b.getCheckOutDate()))
                        .collect(Collectors.toList()));
    }
}
