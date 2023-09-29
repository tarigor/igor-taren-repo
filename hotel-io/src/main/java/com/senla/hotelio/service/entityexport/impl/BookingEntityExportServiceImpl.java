package com.senla.hotelio.service.entityexport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;


@CreateInstanceAndPutInContainer
public class BookingEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "Booking";

    private BookingServiceImpl bookingService;

    @InjectValue(key = "BookingServiceImpl")
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void exportEntity() {
        List<Booking> bookings = bookingService.getAll();
        storeEntityToCsv(ENTITY_FILENAME, bookings);
    }
}
