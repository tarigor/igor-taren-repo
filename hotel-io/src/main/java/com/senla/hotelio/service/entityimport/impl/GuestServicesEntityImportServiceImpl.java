package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GuestServicesEntityImportServiceImpl extends ImportService implements IImportService<GuestServices> {
    public static final String PATTERN = "dd-MM-yyyy";
    private final String ENTITY_NAME = "GuestServices";
    private final GuestServiceImpl guestService;
    private final RoomServicesServiceImpl roomServicesService;

    @Autowired
    public GuestServicesEntityImportServiceImpl(GuestServiceImpl guestService, RoomServicesServiceImpl roomServicesService) {
        this.guestService = guestService;
        this.roomServicesService = roomServicesService;
    }

    @Override
    public ArrayList<GuestServices> importEntities() {
        ArrayList<GuestServices> guestsServices = new ArrayList<>();
        ArrayList<List<String>> guestsServicesWithParameters = getEntitiesFromCsv(ENTITY_NAME);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        for (List<String> guestsServicesWithParameter : guestsServicesWithParameters) {
            guestsServices.add(new GuestServices(
                    Long.parseLong(guestsServicesWithParameter.get(0)),
                    guestService.getById(Long.parseLong(guestsServicesWithParameter.get(1))),
                    roomServicesService.getById(Long.parseLong(guestsServicesWithParameter.get(2))),
                    LocalDate.parse(guestsServicesWithParameter.get(3), dateTimeFormatter))
            );
        }
        return guestsServices;
    }
}
