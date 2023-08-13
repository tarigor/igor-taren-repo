package com.senla.hotelio;

import com.senla.hotel.entity.Guest;
import com.senla.hotelio.service.impl.ImportService;

import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        ImportService importService = new ImportService();
        ArrayList<Guest> guests = importService.getEntitiesFromCsv("Guest");
        guests.forEach(System.out::println);
    }
}
