package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.entity.Guest;
import com.senla.hotelio.service.entityimport.ImportService;
import com.senla.hotelio.service.entityimport.IImportService;

import java.util.ArrayList;
import java.util.List;

public class GuestEntityImportServiceImpl extends ImportService implements IImportService<Guest> {
    private static final GuestEntityImportServiceImpl INSTANCE = new GuestEntityImportServiceImpl();
    private final String ENTITY_NAME = "Guest";

    public static GuestEntityImportServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ArrayList<Guest> importEntities() {
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
