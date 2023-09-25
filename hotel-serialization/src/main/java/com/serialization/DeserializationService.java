package com.serialization;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senla.container.CreateInstanceAndPutInContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class DeserializationService {

    private static final String RESOURCES_PATH = "\\hotel-serialization\\resources";

    private static String readFileToString(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); // Add newline character if needed
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public <T> Map<Long, T> deserializeToMap(Class<T> type, String fileName) {
        String fileContent = readFileToString(System.getProperty("user.dir") + RESOURCES_PATH + "\\" + fileName + ".json");
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<Long, T>>() {
        }.getType();
        Map<Long, T> map = gson.fromJson(fileContent, mapType);
        return map;
    }
}
