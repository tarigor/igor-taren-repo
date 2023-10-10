package com.senla.menu.service;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.menu.action.impl.*;
import com.senla.menu.builder.Builder;
import com.senla.menu.controller.MenuController;
import com.senla.menu.entity.Menu;
import com.senla.menu.entity.MenuItem;
import com.senla.menu.navigator.Navigator;

@CreateInstanceAndPutInContainer
public class MenuService {
    private String MENU_DESCRIPTION_FILENAME = "\\hotel-ui\\src\\main\\resources\\menu.properties";
    private MenuController MENU_CONTROLLER;
    private Builder BUILDER;
    private PropertiesService PROPERTIES_SERVICE;
    private Navigator NAVIGATOR;
    private MenuAction1 menuAction1;
    private MenuAction2 menuAction2;
    private MenuAction3 menuAction3;
    private MenuAction4 menuAction4;
    private MenuAction5 menuAction5;
    private MenuAction6 menuAction6;
    private MenuAction7 menuAction7;
    private MenuAction8 menuAction8;
    private MenuAction9 menuAction9;
    private MenuAction10 menuAction10;
    private MenuAction11 menuAction11;
    private MenuAction12 menuAction12;
    private MenuAction13 menuAction13;
    private MenuAction14 menuAction14;
    private MenuAction15 menuAction15;
    private MenuAction16 menuAction16;
    private MenuAction17 menuAction17;
    private MenuAction18 menuAction18;
    private MenuAction19 menuAction19;

    @InjectValue(key = "MenuController")
    public void setMenuController(MenuController menuController) {
        this.MENU_CONTROLLER = menuController;
    }

    @InjectValue(key = "Builder")
    public void setBUILDER(Builder BUILDER) {
        this.BUILDER = BUILDER;
    }

    @InjectValue(key = "PropertiesService")
    public void setPropertiesService(PropertiesService propertiesService) {
        this.PROPERTIES_SERVICE = propertiesService;
    }

    @InjectValue(key = "Navigator")
    public void setNAVIGATOR(Navigator NAVIGATOR) {
        this.NAVIGATOR = NAVIGATOR;
    }

    @InjectValue(key = "MenuAction1")
    public void setMenuAction1(MenuAction1 menuAction1) {
        this.menuAction1 = menuAction1;
    }

    @InjectValue(key = "MenuAction2")
    public void setMenuAction2(MenuAction2 menuAction2) {
        this.menuAction2 = menuAction2;
    }

    @InjectValue(key = "MenuAction3")
    public void setMenuAction3(MenuAction3 menuAction3) {
        this.menuAction3 = menuAction3;
    }

    @InjectValue(key = "MenuAction4")
    public void setMenuAction4(MenuAction4 menuAction4) {
        this.menuAction4 = menuAction4;
    }

    @InjectValue(key = "MenuAction5")
    public void setMenuAction5(MenuAction5 menuAction5) {
        this.menuAction5 = menuAction5;
    }

    @InjectValue(key = "MenuAction6")
    public void setMenuAction6(MenuAction6 menuAction6) {
        this.menuAction6 = menuAction6;
    }

    @InjectValue(key = "MenuAction7")
    public void setMenuAction7(MenuAction7 menuAction7) {
        this.menuAction7 = menuAction7;
    }

    @InjectValue(key = "MenuAction8")
    public void setMenuAction8(MenuAction8 menuAction8) {
        this.menuAction8 = menuAction8;
    }

    @InjectValue(key = "MenuAction9")
    public void setMenuAction9(MenuAction9 menuAction9) {
        this.menuAction9 = menuAction9;
    }

    @InjectValue(key = "MenuAction10")
    public void setMenuAction10(MenuAction10 menuAction10) {
        this.menuAction10 = menuAction10;
    }

    @InjectValue(key = "MenuAction11")
    public void setMenuAction11(MenuAction11 menuAction11) {
        this.menuAction11 = menuAction11;
    }

    @InjectValue(key = "MenuAction12")
    public void setMenuAction12(MenuAction12 menuAction12) {
        this.menuAction12 = menuAction12;
    }

    @InjectValue(key = "MenuAction13")
    public void setMenuAction13(MenuAction13 menuAction13) {
        this.menuAction13 = menuAction13;
    }

    @InjectValue(key = "MenuAction14")
    public void setMenuAction14(MenuAction14 menuAction14) {
        this.menuAction14 = menuAction14;
    }

    @InjectValue(key = "MenuAction15")
    public void setMenuAction15(MenuAction15 menuAction15) {
        this.menuAction15 = menuAction15;
    }

    @InjectValue(key = "MenuAction16")
    public void setMenuAction16(MenuAction16 menuAction16) {
        this.menuAction16 = menuAction16;
    }

    @InjectValue(key = "MenuAction17")
    public void setMenuAction17(MenuAction17 menuAction17) {
        this.menuAction17 = menuAction17;
    }

    @InjectValue(key = "MenuAction18")
    public void setMenuAction18(MenuAction18 menuAction18) {
        this.menuAction18 = menuAction18;
    }

    @InjectValue(key = "MenuAction19")
    public void setMenuAction19(MenuAction19 menuAction19) {
        this.menuAction19 = menuAction19;
    }

    public void showMenu() {
        Menu menu = BUILDER
                .setTitle("HOTEL OPERATION")
                //        1=List of rooms sorted by price
                .addItem(new MenuItem(1, menuAction1))
                //        2=List of rooms sorted by capacity
                .addItem(new MenuItem(2, menuAction2))
                //        3=List of rooms sorted by number of stars
                .addItem(new MenuItem(3, menuAction3))
                //        4=List of guests and their rooms sorted alphabetically
                .addItem(new MenuItem(4, menuAction4))
                //        5=List of guests and their rooms sorted by check-out date
                .addItem(new MenuItem(5, menuAction5))
                //        6=Total number of available rooms
                .addItem(new MenuItem(6, menuAction6))
                //        7=Total number of guests
                .addItem(new MenuItem(7, menuAction7))
                //        8=List of rooms that will be available on a certain date in the future
                .addItem(new MenuItem(8, menuAction8))
                //        9=The amount of payment for the room to be paid by the guest
                .addItem(new MenuItem(9, menuAction9))
                //        10=View the last 3 guests of the room and the dates of their stay
                .addItem(new MenuItem(10, menuAction10))
                //        11=View the list of guest services and their price (sorted by PRICE,DATE) in ASC(DESC) manner
                .addItem(new MenuItem(11, menuAction11))
                //        12=Prices of services and rooms (sorted by CAPACITY,PRICE,AVAILABILITY,SERVICE,RATING) in ASC(DESC) manner
                .addItem(new MenuItem(12, menuAction12))
                //        13=Room services (ordered by ROME_SERVICES,PRICE) in ASC(DESC) manner
                .addItem(new MenuItem(13, menuAction13))
                //        14=Show the details of a separate room
                .addItem(new MenuItem(14, menuAction14))
                //        15=Import the certain entity from the CSV file
                .addItem(new MenuItem(15, menuAction15))
                //        16=Export the certain entity
                .addItem(new MenuItem(16, menuAction16))
                .addItem(new MenuItem(17, menuAction17))
                .addItem(new MenuItem(18, menuAction18))
                .addItem(new MenuItem(19, menuAction19))
                .build();


        NAVIGATOR.setPropertiesService(PROPERTIES_SERVICE);

        MENU_CONTROLLER.setMenu(menu);
        MENU_CONTROLLER.setNavigator(NAVIGATOR);
        MENU_CONTROLLER.setMenuDescriptionFileName(MENU_DESCRIPTION_FILENAME);
        MENU_CONTROLLER.setBuilder(BUILDER);
        MENU_CONTROLLER.showMenu()
                .menuRolling();
    }
}
