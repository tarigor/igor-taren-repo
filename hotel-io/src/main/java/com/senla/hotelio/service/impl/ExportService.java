package com.senla.hotelio.service.impl;

import com.senla.hotel.dao.impl.GuestServicesDAOImpl;
import com.senla.hotel.entity.*;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hotelio.service.IExportService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExportService implements IExportService {
    public static final String PATH = "hotel-io/csv/export/";
    public static final String EXTENSION = ".csv";
    public static final String REGEX = "(?<=\\=)(.*?)(?=\\,)";
    private static final ExportService INSTANCE = new ExportService();

    public static ExportService getInstance() {
        return INSTANCE;
    }

    @Override
    public void storeEntityToCsv(String entityClassName) {
        final Path path = Paths.get(PATH + entityClassName + EXTENSION);
        switch (entityClassName) {
            case "Booking": {
                try {
                    Files.deleteIfExists(path);
                    for (Booking booking : BookingServiceImpl.getInstance().getAll()) {
                        Files.write(path, getFieldsFromEntityInCsvFormat(booking).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case "Guest": {
                try {
                    Files.deleteIfExists(path);
                    for (Guest guest : GuestServiceImpl.getInstance().getAll()) {
                        Files.write(path, getFieldsFromEntityInCsvFormat(guest).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case "Room": {
                try {
                    Files.deleteIfExists(path);
                    for (Room room : RoomServiceImpl.getInstance().getAll()) {
                        Files.write(path, getFieldsFromEntityInCsvFormat(room).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case "RoomService": {
                try {
                    Files.deleteIfExists(path);
                    for (RoomService roomService : RoomServicesServiceImpl.getInstance().getAll()) {
                        Files.write(path, getFieldsFromEntityInCsvFormat(roomService).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case "GuestServices": {
                try {
                    Files.deleteIfExists(path);
                    for (GuestServices guestServices : GuestServicesDAOImpl.getInstance().getAll()) {
                        Files.write(path, getFieldsFromEntityInCsvFormat(guestServices).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            default:
                System.out.println("There is no such an entity ->" + entityClassName);
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
