package com.senla.hotelio.service.entityexport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dto.entityexport.BookingExport;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

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
    public void exportEntity() {
        List<Booking> bookings = bookingService.getAll();
        storeEntityToCsv(
                ENTITY_FILENAME,
                bookings.stream()
                        .map(b -> new BookingExport(b.getId(), b.getGuest().getId(), b.getRoom().getId(), b.getCheckInDate(), b.getCheckOutDate()))
                        .collect(Collectors.toList()));
    }
}
