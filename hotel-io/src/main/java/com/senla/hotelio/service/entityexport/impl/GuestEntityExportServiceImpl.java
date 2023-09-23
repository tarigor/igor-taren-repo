package com.senla.hotelio.service.entityexport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;
@CreateInstanceAndPutInContainer
public class GuestEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "Guest";
    @Override
    public void exportEntity() {
        List<Guest> guests = GuestServiceImpl.getInstance().getAll();
        storeEntityToCsv(ENTITY_FILENAME, guests);
    }
}
