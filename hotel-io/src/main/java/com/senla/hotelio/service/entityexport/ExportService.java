package com.senla.hotelio.service.entityexport;

import com.senla.hotelio.service.exception.HotelIoModuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public abstract class ExportService {
    private static final String EXPORT_PATH = "\\hotel-io\\src\\main\\resources\\csv\\export";
    private static final String EXTENSION = ".csv";
    private static final String REGEX = "=([^,}]+)";
    @Value("${csv.export.path}")
    private String csvExportPath;

    protected <T> void storeEntityToCsv(String entityFileName, List<T> list) throws HotelIoModuleException {
        try {
            Path path = Paths.get(csvExportPath + entityFileName + EXTENSION);
            Files.deleteIfExists(path);
            for (T o : list) {
                Files.write(path, getFieldsFromEntityInCsvFormat(o).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            log.error("an error occurred during file operation -> {}", e.getMessage());
            System.out.println(e.getMessage());
            throw new HotelIoModuleException(e);
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
