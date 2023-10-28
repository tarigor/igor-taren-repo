package com.senla.hotelio.service.entityimport;

import com.senla.hotelio.service.exception.HotelIoModuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ImportService {
    private static final Logger logger = LoggerFactory.getLogger(ImportService.class);
    private static final String DELIMITER = ",";
    private static final String EXTENSION = ".csv";
    @Autowired
    private ResourceLoader resourceLoader;

    protected ArrayList<List<String>> getEntitiesFromCsv(String fileName) throws HotelIoModuleException {
        ArrayList<List<String>> entities = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:csv/import/" + fileName + EXTENSION);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (line.charAt(0) == '#') continue;
                entities.add(Arrays.asList(line.split(DELIMITER)));
            }
        } catch (IOException e) {
            logger.error("an error occurred during a file operation -> {}", e.getMessage());
            throw new HotelIoModuleException(e);
        }
        return entities;
    }
}
