package com.senla.hotelio.service.impl;

import com.senla.hotelio.service.IExportService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExportService implements IExportService {

    public static final String PATH = "hotel-io/csv/export/";
    public static final String EXTENSION = ".csv";
    public static final String REGEX = "(?<=\\=)(.*?)(?=\\,)";

    @Override
    public void storeEntityToCsv(Object entity) {
        Class<?> aClass = entity.getClass();
        String className = aClass.getName().split("\\.")[aClass.getName().split("\\.").length - 1];
        try {
            Files.write(Paths.get(PATH + className + EXTENSION), getFieldsFromEntityInCsvFormat(entity).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFieldsFromEntityInCsvFormat(Object entity) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(entity.toString().replace("}", ","));
        StringBuilder resultText = new StringBuilder();
        while (matcher.find()) {
            String result = matcher.group(1).replace("'", "");
            resultText.append(result).append(",");
        }
        return resultText.substring(0, resultText.length() - 1) + "\n";
    }
}
