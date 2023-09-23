package com.senla.hotelio.service.entityexport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.dto.GuestServicesEntityDTO;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestServicesEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "GuestServices";
    @Override
    public void exportEntity() {
        List<GuestServicesEntityDTO> guestServices = GuestServicesServiceImpl.getInstance().getAll();
        storeEntityToCsv(ENTITY_FILENAME, guestServices);
    }
}
