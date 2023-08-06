package com.senla.hotelio;

import com.senla.hotel.entity.Guest;
import com.senla.hotel.entity.Room;
import com.senla.hotelio.service.impl.ExportService;
import com.senla.hotelio.service.impl.ImportService;

import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        ImportService importService = new ImportService();
        ArrayList<Guest> guests = importService.getEntitiesFromCsv("Guest");
        guests.forEach(System.out::println);

        ExportService exportService = new ExportService();
        exportService.storeEntityToCsv(new Guest(2,"dd","ss"));
        exportService.storeEntityToCsv(new Room(1, 23.2, false, 1, 3));
    }
}
