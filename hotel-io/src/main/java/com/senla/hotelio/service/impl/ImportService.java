package com.senla.hotelio.service.impl;

import com.senla.hotel.entity.Guest;
import com.senla.hotel.entity.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportService {
    public <T> ArrayList<T> getEntitiesFromCsv(String fileName) {
        ArrayList<T> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("hotel-io/csv/" + fileName + ".csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() == 0) continue;
                if (line.charAt(0) == '#') continue;
                entities.add((T) createInstance(fileName, line.split(",")));
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while handling a file with a filename -> " + "\n" + e.getMessage());
        }
        return entities;
    }

    Object createInstance(String fileName, String... parameters) {
        try {
            Class<?> aClass = Class.forName("com.senla.hotel.entity." + fileName);
            if (aClass.equals(Guest.class)) {
                return new Guest(Long.parseLong(parameters[0]), parameters[1], parameters[2]);
            } else if (aClass.equals(Room.class)) {
                return new Room(
                        Long.parseLong(parameters[0]),
                        Integer.parseInt(parameters[1]),
                        Double.parseDouble(parameters[2]),
                        Boolean.parseBoolean(parameters[3]),
                        Long.parseLong(parameters[4]),
                        Integer.parseInt(parameters[5]));
            } else {
                throw new RuntimeException("class not found");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not found");
        }
    }
}
