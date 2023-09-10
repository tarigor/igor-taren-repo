package com.senla.hotelio.service.entityexport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExportService {
    private static final String EXPORT_PATH = "\\hotel-io\\resources\\csv\\export";
    private static final String EXTENSION = ".csv";
    private static final String REGEX = "(?<=\\=)(.*?)(?=\\,)";

    protected <T> void storeEntityToCsv(String entityFileName, List<T> list) {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + EXPORT_PATH + "\\" + entityFileName + EXTENSION);
            Files.deleteIfExists(path);
            for (T o : list) {
                Files.write(path, getFieldsFromEntityInCsvFormat(o).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFieldsFromEntityInCsvFormat(Object entity) {
        System.out.println("entity->" + entity.toString());
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
