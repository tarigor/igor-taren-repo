package com.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class DeserializationService {
    private static final Logger logger = LoggerFactory.getLogger(DeserializationService.class);
    private static final String RESOURCES_PATH = "\\hotel-serialization\\src\\main\\resources";

    private static String readFileToString(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.error("an error occurred during an IO operation -> {}", e.getMessage());
            e.printStackTrace();
        }
        return content.toString();
    }

    public <T> Map<Long, T> deserializeToMap(Class<T> type, String fileName) {
        String fileContent = readFileToString(System.getProperty("user.dir") + RESOURCES_PATH + "\\" + fileName + ".json");
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new CustomDateDeserializer());
        objectMapper.registerModule(module);
        try {
            return objectMapper.readValue(
                    fileContent,
                    objectMapper.getTypeFactory().constructMapType(HashMap.class, Long.class, type)
            );
        } catch (JsonProcessingException e) {
            logger.error("an error occurred during an JSON operation -> {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
