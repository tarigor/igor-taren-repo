package com.senla.hotelio.service.entityimport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.entity.Booking;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class BookingEntityImportServiceImpl extends ImportService implements IImportService<Booking> {
    private final String ENTITY_NAME = "Booking";

    @Override
    public ArrayList<Booking> importEntities() {
        ArrayList<Booking> bookings = new ArrayList<>();
        ArrayList<List<String>> bookingsWithParameters = getEntitiesFromCsv(ENTITY_NAME);
        for (List<String> bookingsWithParameter : bookingsWithParameters) {
            try {
                bookings.add(new Booking(
                        Long.parseLong(bookingsWithParameter.get(0)),
                        Long.parseLong(bookingsWithParameter.get(1)),
                        Long.parseLong(bookingsWithParameter.get(2)),
                        new SimpleDateFormat("dd/MM/yyyy").parse(bookingsWithParameter.get(3)),
                        new SimpleDateFormat("dd/MM/yyyy").parse(bookingsWithParameter.get(4))
                ));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return bookings;
    }
}
