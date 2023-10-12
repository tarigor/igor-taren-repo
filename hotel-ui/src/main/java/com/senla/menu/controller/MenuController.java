package com.senla.menu.controller;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.menu.builder.Builder;
import com.senla.menu.entity.Menu;
import com.senla.menu.navigator.Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

@CreateInstanceAndPutInContainer
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    private Scanner scanner = new Scanner(System.in);
    private Menu menu;
    private Navigator navigator;
    private String menuDescriptionFileName;
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

    public void setMenuDescriptionFileName(String menuDescriptionFileName) {
        this.menuDescriptionFileName = menuDescriptionFileName;
    }

    public MenuController showMenu() {
        navigator.navigate(menu, this.menuDescriptionFileName);
        return this;
    }

    public void menuRolling() {
        System.out.println("0. Exit");
        System.out.println("select an option");
        int item = scanner.nextInt();
        if (item != 0) {
            try {
                builder.getItems().get(item).execute();
            } catch (NullPointerException e) {
                logger.error("There is no such an item->" + item + " , please select an item within the provided menu");
            }
            System.out.println("----------------------------------");
            showMenu();
            menuRolling();
        }
    }
}
