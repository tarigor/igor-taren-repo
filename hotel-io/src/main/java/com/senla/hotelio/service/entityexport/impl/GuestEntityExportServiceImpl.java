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
public class GuestEntityExportServiceImpl implements IExportService {
    private final String ENTITY_FILENAME = "Guest";

    private final GuestServiceImpl guestService;
    private final ExportService exportService;
    @Autowired
    public GuestEntityExportServiceImpl(GuestServiceImpl guestService, ExportService exportService) {
        this.guestService = guestService;
        this.exportService = exportService;
    }

    @Override
    public void exportEntity() {
        List<Guest> guests = guestService.getAll();
        exportService.storeEntityToCsv(ENTITY_FILENAME, guests);
    }
}
