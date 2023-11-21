package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.dto.entityexport.GuestServiceExport;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestServicesEntityExportServiceImpl implements IExportService {
    private final String ENTITY_FILENAME = "GuestServices";
    private final GuestServicesServiceImpl guestServicesService;
    private final ExportService exportService;
    @Autowired
    public GuestServicesEntityExportServiceImpl(GuestServicesServiceImpl guestServicesService, ExportService exportService) {
        this.guestServicesService = guestServicesService;
        this.exportService = exportService;
    }

    @Override
    public void exportEntity() {
        List<GuestServices> guestServices = guestServicesService.getAll();
        exportService.storeEntityToCsv(ENTITY_FILENAME,
                guestServices.stream()
                        .map(gs -> new GuestServiceExport(gs.getId(), gs.getGuest().getId(), gs.getRoomService().getId(), gs.getRoomServiceOrderDate()))
                        .collect(Collectors.toList()));
    }
}
