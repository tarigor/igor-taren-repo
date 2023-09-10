package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.entity.Guest;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;

public class GuestEntityExportServiceImpl extends ExportService implements IExportService {
    private static final GuestEntityExportServiceImpl INSTANCE = new GuestEntityExportServiceImpl();
    private final String ENTITY_FILENAME = "Guest";
    private final List<Guest> guests = GuestServiceImpl.getInstance().getAll();

    public static GuestEntityExportServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void exportEntity() {
        storeEntityToCsv(ENTITY_FILENAME, guests);
    }
}
