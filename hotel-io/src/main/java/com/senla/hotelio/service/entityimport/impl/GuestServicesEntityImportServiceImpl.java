package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GuestServicesEntityImportServiceImpl extends ImportService implements IImportService<GuestServices> {
    private final String ENTITY_NAME = "GuestServices";
    private GuestServiceImpl guestService;
    private RoomServicesServiceImpl roomServicesService;

    @Autowired
    public void setGuestService(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @Autowired
    public void setRoomServicesService(RoomServicesServiceImpl roomServicesService) {
        this.roomServicesService = roomServicesService;
    }

    @Override
    public ArrayList<GuestServices> importEntities() throws HotelIoModuleException {
        ArrayList<GuestServices> guestsServices = new ArrayList<>();
        ArrayList<List<String>> guestsServicesWithParameters = getEntitiesFromCsv(ENTITY_NAME);
        for (List<String> guestsServicesWithParameter : guestsServicesWithParameters) {
            try {
                guestsServices.add(new GuestServices(
                        Long.parseLong(guestsServicesWithParameter.get(0)),
                        guestService.getById(Long.parseLong(guestsServicesWithParameter.get(1))),
                        roomServicesService.getById(Long.parseLong(guestsServicesWithParameter.get(2))),
                        new SimpleDateFormat("yyyy-MM-dd").parse(guestsServicesWithParameter.get(3))
                ));
            } catch (ParseException e) {
                log.error("an error occurred during a parse operation -> {}", e.getMessage());
            }
        }
        return guestsServices;
    }
}
