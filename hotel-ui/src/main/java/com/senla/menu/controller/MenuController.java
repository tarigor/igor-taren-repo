package com.senla.menu.controller;

import com.senla.hotel.exception.HotelModuleException;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import com.senla.menu.builder.Builder;
import com.senla.menu.entity.Menu;
import com.senla.menu.exception.HotelUiModuleException;
import com.senla.menu.navigator.Navigator;
import com.senla.serialization.exception.HotelSerializationModuleException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuController {
    private final Scanner scanner = new Scanner(System.in);
    private Menu menu;
    private Navigator navigator;
    private Builder builder;

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public MenuController showMenu() throws HotelUiModuleException {
        navigator.navigate(menu);
        return this;
    }

    public void menuRolling() throws HotelUiModuleException, HotelSerializationModuleException, HotelIoModuleException, HotelModuleException {
        System.out.println("0. Exit");
        System.out.println("select an option");
        int item = scanner.nextInt();
        if (item != 0) {
            System.out.println("----------------------------------");
            builder.getItems().get(item).execute();
            System.out.println("----------------------------------");
            showMenu();
            menuRolling();
        }
    }
}
