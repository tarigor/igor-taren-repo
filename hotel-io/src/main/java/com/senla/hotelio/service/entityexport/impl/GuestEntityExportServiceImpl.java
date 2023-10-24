package com.senla.hotelio.service.entityexport.impl;

import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.betterthenspring.annotation.InjectValue;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hoteldb.entity.Guest;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;

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
    public void exportEntity() throws HotelIoModuleException {
        List<Guest> guests = guestService.getAll();
        storeEntityToCsv(ENTITY_FILENAME, guests);
    }
}
