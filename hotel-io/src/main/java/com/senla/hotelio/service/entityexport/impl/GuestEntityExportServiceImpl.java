package com.senla.hotelio.service.entityexport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "Guest";

    private GuestServiceImpl guestService;

    @InjectValue
    public void setGuestService(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @Override
    public void exportEntity() {
        List<Guest> guests = guestService.getAll();
        storeEntityToCsv(ENTITY_FILENAME, guests);
    }
}
