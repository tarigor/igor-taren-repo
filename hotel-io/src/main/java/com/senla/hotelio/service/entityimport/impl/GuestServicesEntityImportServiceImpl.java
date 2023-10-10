package com.senla.hotelio.service.entityimport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestServicesEntityImportServiceImpl extends ImportService implements IImportService<GuestServices> {
    private static final Logger logger = LoggerFactory.getLogger(GuestServicesEntityImportServiceImpl.class);
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
                logger.error("an error occurred during a parse operation->" + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return guestsServices;
    }
}
