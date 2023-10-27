package com.senla.menu.service;

import com.senla.menu.exception.HotelUiModuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Service
public class PropertiesService {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesService.class);
    private final Map<Integer, String> menuMap = new TreeMap<>();

    public Map<Integer, String> readPropertiesFileAsMap(String filename, String delimiter) throws HotelUiModuleException {
        try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() == 0) continue;
                if (line.charAt(0) == '#') continue;
                menuMap.put(Integer.parseInt(line.split(delimiter)[0]), line.split(delimiter)[1]);
            }
        } catch (IOException e) {
            logger.error("an error occurred during an IO operation -> {}", e.getMessage());
            throw new HotelUiModuleException(e);
        }
        return menuMap;
    }
}