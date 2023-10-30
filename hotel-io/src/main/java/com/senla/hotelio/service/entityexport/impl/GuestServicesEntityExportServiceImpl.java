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
public class GuestServicesEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "GuestServices";
    private GuestServicesServiceImpl guestServicesService;

    @Autowired
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    @Override
    public void exportEntity() throws HotelIoModuleException {
        List<GuestServices> guestServices = guestServicesService.getAll();
        storeEntityToCsv(ENTITY_FILENAME,
                guestServices.stream()
                        .map(gs -> new GuestServiceExport(gs.getId(), gs.getGuest().getId(), gs.getRoomService().getId(), gs.getRoomServiceOrderDate()))
                        .collect(Collectors.toList()));
    }
}
