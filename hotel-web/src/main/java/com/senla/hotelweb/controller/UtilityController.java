package com.senla.hotelweb.controller;

import com.senla.hotel.validator.annotation.EnumValidator;
import com.senla.hotelio.service.constant.EntityName;
import com.senla.hotelio.service.entityexport.impl.CsvExportServiceImpl;
import com.senla.hotelio.service.entityimport.impl.CsvImportServiceImpl;
import com.senla.serialization.service.DeserializationService;
import com.senla.serialization.service.SerializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utility")
@Validated
public class UtilityController {
    @Autowired
    private CsvImportServiceImpl csvImportService;
    @Autowired
    private CsvExportServiceImpl csvExportService;
    @Autowired
    private DeserializationService deserializationService;
    @Autowired
    private SerializationService serializationService;

    //15=Import the certain entity from the CSV file
    @GetMapping("/csv/importing/{entity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void importFromCsv(@PathVariable @EnumValidator(targetClassType = EntityName.class) String entity) {
        csvImportService.importEntity(entity);
    }

    //16=Export the certain entity
    @GetMapping("/csv/exporting/{entity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exportFromCsv(@PathVariable @EnumValidator(targetClassType = EntityName.class) String entity) {
        csvExportService.exportEntity(entity);
    }

    //17=Do serialization of entity
    @GetMapping("/serialization/{entity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void serialize(@PathVariable @EnumValidator(targetClassType = EntityName.class) String entity) {
        serializationService.serialize(entity);
    }

    //18=Do de-serialization of entity
    @GetMapping("/deserialization/{entity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deserialize(@PathVariable @EnumValidator(targetClassType = EntityName.class) String entity) {
        deserializationService.deserialize(entity);
    }
}
