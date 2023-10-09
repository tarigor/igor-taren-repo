package com.senla.menu.service;

import com.senla.container.CreateInstanceAndPutInContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@CreateInstanceAndPutInContainer
public class PropertiesService {
    private final Map<Integer, String> menuMap = new TreeMap<>();

    public Map<Integer, String> readPropertiesFileAsMap(String filename, String delimiter) {
        System.out.println("fileName->" + filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() == 0) continue;
                if (line.charAt(0) == '#') continue;
                menuMap.put(Integer.parseInt(line.split(delimiter)[0]), line.split(delimiter)[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while handling a file with a filename -> " + filename
                    + "\n" + e.getMessage());
        }
        return menuMap;
    }


}
