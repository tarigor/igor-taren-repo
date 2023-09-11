package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.entity.Booking;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;

public class BookingEntityExportServiceImpl extends ExportService implements IExportService {
    private static final BookingEntityExportServiceImpl INSTANCE = new BookingEntityExportServiceImpl();
    private final String ENTITY_FILENAME = "Booking";
    private final List<Booking> bookings = BookingServiceImpl.getInstance().getAll();

    public static BookingEntityExportServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void exportEntity() {
        storeEntityToCsv(ENTITY_FILENAME, bookings);
    }
}
