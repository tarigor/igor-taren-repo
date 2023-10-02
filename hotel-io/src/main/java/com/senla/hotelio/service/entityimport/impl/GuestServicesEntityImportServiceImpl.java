package com.senla.hotelio.service.entityimport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            try {
                guestsServices.add(new GuestServices(
                        Long.parseLong(guestsServicesWithParameter.get(0)),
                        Long.parseLong(guestsServicesWithParameter.get(1)),
                        Long.parseLong(guestsServicesWithParameter.get(2)),
                        new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(guestsServicesWithParameter.get(3))
                ));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return guestsServices;
    }
}
