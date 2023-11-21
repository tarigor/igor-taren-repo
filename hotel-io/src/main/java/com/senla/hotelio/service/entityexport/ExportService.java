package com.senla.hotelio.service.entityexport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ExportService {
    private static final String EXTENSION = ".csv";
    private static final String REGEX = "=([^,}]+)";
    @Value("${csv.export.path}")
    private String csvExportPath;

    public void setCsvExportPath(String csvExportPath) {
        this.csvExportPath = csvExportPath;
    }

    public <T> void storeEntityToCsv(String entityFileName, List<T> list) {
        try {
            Path path = Paths.get(csvExportPath + entityFileName + EXTENSION);
            Files.deleteIfExists(path);
            for (T o : list) {
                Files.write(path, getFieldsFromEntityInCsvFormat(o).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            log.error("an error occurred during file operation -> {}", e.getMessage());
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
