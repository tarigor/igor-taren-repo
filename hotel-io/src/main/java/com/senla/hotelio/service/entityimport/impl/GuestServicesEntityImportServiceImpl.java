package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.entity.GuestServices;
import com.senla.hotelio.service.entityimport.ImportService;
import com.senla.hotelio.service.entityimport.IImportService;

import java.util.ArrayList;
import java.util.List;

public class GuestServicesEntityImportServiceImpl extends ImportService implements IImportService<GuestServices> {
    private static final GuestServicesEntityImportServiceImpl INSTANCE = new GuestServicesEntityImportServiceImpl();
    private final String ENTITY_NAME = "GuestServices";

    public static GuestServicesEntityImportServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ArrayList<GuestServices> importEntities() {
        ArrayList<GuestServices> guestsServices = new ArrayList<>();
        ArrayList<List<String>> guestsServicesWithParameters = getEntitiesFromCsv(ENTITY_NAME);
        for (List<String> guestsServicesWithParameter : guestsServicesWithParameters) {
            guestsServices.add(new GuestServices(
                    Long.parseLong(guestsServicesWithParameter.get(0)),
                    Long.parseLong(guestsServicesWithParameter.get(1)),
                    guestsServicesWithParameter.get(2)
            ));
        }
        return guestsServices;
    }
}
