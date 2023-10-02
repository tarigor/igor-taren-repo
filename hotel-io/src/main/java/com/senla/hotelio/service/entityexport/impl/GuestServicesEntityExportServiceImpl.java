package com.senla.hotelio.service.entityexport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestServicesEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "GuestServices";
    private GuestServicesServiceImpl guestServicesService;

    @InjectValue(key = "GuestServicesServiceImpl")
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    @Override
    public void exportEntity() {
        List<GuestServices> guestServices = guestServicesService.getAll();
        storeEntityToCsv(ENTITY_FILENAME, guestServices);
    }
}
