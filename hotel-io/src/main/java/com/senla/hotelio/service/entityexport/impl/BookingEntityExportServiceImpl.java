package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.dto.entityexport.BookingExport;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hoteldb.entity.Booking;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookingEntityExportServiceImpl implements IExportService {
    private final String ENTITY_FILENAME = "Booking";

    private final BookingServiceImpl bookingService;
    private final ExportService exportService;

    @Autowired
    public BookingEntityExportServiceImpl(BookingServiceImpl bookingService, ExportService exportService) {
        this.bookingService = bookingService;
        this.exportService = exportService;
    }

    @Override
    public void exportEntity() {
        List<Booking> bookings = bookingService.getAll();
        exportService.storeEntityToCsv(
                ENTITY_FILENAME,
                bookings.stream()
                        .map(b -> new BookingExport(b.getId(), b.getGuest().getId(), b.getRoom().getId(), b.getCheckInDate(), b.getCheckOutDate()))
                        .collect(Collectors.toList()));
    }
}
