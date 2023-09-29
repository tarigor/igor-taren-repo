package com.senla.hotelio.service.entityimport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;

import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestServicesEntityImportServiceImpl extends ImportService implements IImportService<GuestServices> {
    private final String ENTITY_NAME = "GuestServices";

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
