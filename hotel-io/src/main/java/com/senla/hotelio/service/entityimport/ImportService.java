package com.senla.hotelio.service.entityimport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ImportService {

    private static final Logger logger = LoggerFactory.getLogger(ImportService.class);
    private static final String IMPORT_PATH = "\\hotel-io\\src\\main\\resources\\csv\\import";
    private static final String DELIMITER = ",";
    private static final String EXTENSION = ".csv";

    protected ArrayList<List<String>> getEntitiesFromCsv(String fileName) {
        ArrayList<List<String>> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + IMPORT_PATH + "\\" + fileName + EXTENSION))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (line.charAt(0) == '#') continue;
                entities.add(Arrays.asList(line.split(DELIMITER)));
            }
        } catch (IOException e) {
            logger.error("an error occurred during a file operation -> {}", e.getMessage());
            throw new RuntimeException("An error occurred while handling a file with a filename -> " + "\n" + e.getMessage());
        }
        return entities;
    }
}
