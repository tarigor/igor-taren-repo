package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.dto.GuestServicesEntityDTO;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;

public class GuestServicesEntityExportServiceImpl extends ExportService implements IExportService {
    private static final GuestServicesEntityExportServiceImpl INSTANCE = new GuestServicesEntityExportServiceImpl();
    private final String ENTITY_FILENAME = "GuestServices";
    private final List<GuestServicesEntityDTO> guestServices = GuestServicesServiceImpl.getInstance().getAll();

    public static GuestServicesEntityExportServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void exportEntity() {
        storeEntityToCsv(ENTITY_FILENAME, guestServices);
    }
}
