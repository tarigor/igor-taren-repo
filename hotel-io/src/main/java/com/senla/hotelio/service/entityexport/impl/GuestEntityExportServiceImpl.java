package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hoteldb.entity.Guest;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "Guest";

    private final GuestServiceImpl guestService;
    @Autowired
    public GuestEntityExportServiceImpl(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @Override
    public void exportEntity() {
        List<Guest> guests = guestService.getAll();
        storeEntityToCsv(ENTITY_FILENAME, guests);
    }
}
