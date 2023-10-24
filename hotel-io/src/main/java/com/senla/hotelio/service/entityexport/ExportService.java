package com.senla.hotelio.service.entityexport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExportService {

    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);
    private static final String EXPORT_PATH = "\\hotel-io\\src\\main\\resources\\csv\\export";
    private static final String EXTENSION = ".csv";
    private static final String REGEX = "=([^,}]+)";

    protected <T> void storeEntityToCsv(String entityFileName, List<T> list) {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + EXPORT_PATH + "\\" + entityFileName + EXTENSION);
            Files.deleteIfExists(path);
            for (T o : list) {
                Files.write(path, getFieldsFromEntityInCsvFormat(o).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            logger.error("an error occurred during file operation -> {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String getFieldsFromEntityInCsvFormat(Object entity) {
        String input = entity.toString().replace(")", "");
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String value = matcher.group(1);
            result.append(value).append(",");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result + "\n";
    }
}
