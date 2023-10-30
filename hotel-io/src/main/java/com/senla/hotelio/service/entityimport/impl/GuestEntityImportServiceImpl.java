package com.senla.hotelio.service.entityimport.impl;

import com.senla.hoteldb.entity.Guest;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestEntityImportServiceImpl extends ImportService implements IImportService<Guest> {
    private final String ENTITY_NAME = "Guest";

    @Override
    public ArrayList<Guest> importEntities() throws HotelIoModuleException {
        ArrayList<Guest> guests = new ArrayList<>();
        ArrayList<List<String>> guestsWithParameters = getEntitiesFromCsv(ENTITY_NAME);
        for (List<String> guestsWithParameter : guestsWithParameters) {
            guests.add(new Guest(
                    Long.parseLong(guestsWithParameter.get(0)),
                    guestsWithParameter.get(1),
                    guestsWithParameter.get(2)
            ));
        }
        return guests;
    }
}
