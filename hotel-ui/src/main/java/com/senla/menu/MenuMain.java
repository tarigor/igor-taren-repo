package com.senla.menu;

import com.senla.betterthenspring.ContainerService;
import com.senla.betterthenspring.PropertiesInjectionService;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.impl.*;
import com.senla.hoteldb.DatabaseService;
import com.senla.menu.action.impl.*;
import com.senla.menu.builder.Builder;
import com.senla.menu.controller.MenuController;
import com.senla.menu.entity.Menu;
import com.senla.menu.entity.MenuItem;
import com.senla.menu.navigator.Navigator;
import com.senla.menu.service.PropertiesService;
import com.serialization.InitializationService;
import com.serialization.SerializationService;

@CreateInstanceAndPutInContainer
public class MenuMain {
    private static String MENU_DESCRIPTION_FILENAME = "menu.properties";
    private static MenuController MENU_CONTROLLER;
    private static Builder BUILDER;
    private static PropertiesService PROPERTIES_SERVICE;
    private static Navigator NAVIGATOR;
    private static MenuAction1 menuAction1;
    private static MenuAction2 menuAction2;
    private static MenuAction3 menuAction3;
    private static MenuAction4 menuAction4;
    private static MenuAction5 menuAction5;
    private static MenuAction6 menuAction6;
    private static MenuAction7 menuAction7;
    private static MenuAction8 menuAction8;
    private static MenuAction9 menuAction9;
    private static MenuAction10 menuAction10;
    private static MenuAction11 menuAction11;
    private static MenuAction12 menuAction12;
    private static MenuAction13 menuAction13;
    private static MenuAction14 menuAction14;
    private static MenuAction15 menuAction15;
    private static MenuAction16 menuAction16;
    private static MenuAction17 menuAction17;
    private static MenuAction18 menuAction18;
    private static MenuAction19 menuAction19;
    private static SerializationService serializationService;
    private static InitializationService initializationService;
    private static DatabaseService databaseService;

    @InjectValue(key = "MenuController")
    public static void setMenuController(MenuController menuController) {
        MENU_CONTROLLER = menuController;
    }

    @InjectValue(key = "Builder")
    public static void setBUILDER(Builder BUILDER) {
        MenuMain.BUILDER = BUILDER;
    }

    @InjectValue(key = "PropertiesService")
    public static void setPropertiesService(PropertiesService propertiesService) {
        PROPERTIES_SERVICE = propertiesService;
    }

    @InjectValue(key = "Navigator")
    public static void setNAVIGATOR(Navigator NAVIGATOR) {
        MenuMain.NAVIGATOR = NAVIGATOR;
    }

    @InjectValue(key = "MenuAction1")
    public static void setMenuAction1(MenuAction1 menuAction1) {
        MenuMain.menuAction1 = menuAction1;
    }

    @InjectValue(key = "MenuAction2")
    public static void setMenuAction2(MenuAction2 menuAction2) {
        MenuMain.menuAction2 = menuAction2;
    }

    @InjectValue(key = "MenuAction3")
    public static void setMenuAction3(MenuAction3 menuAction3) {
        MenuMain.menuAction3 = menuAction3;
    }

    @InjectValue(key = "MenuAction4")
    public static void setMenuAction4(MenuAction4 menuAction4) {
        MenuMain.menuAction4 = menuAction4;
    }

    @InjectValue(key = "MenuAction5")
    public static void setMenuAction5(MenuAction5 menuAction5) {
        MenuMain.menuAction5 = menuAction5;
    }

    @InjectValue(key = "MenuAction6")
    public static void setMenuAction6(MenuAction6 menuAction6) {
        MenuMain.menuAction6 = menuAction6;
    }

    @InjectValue(key = "MenuAction7")
    public static void setMenuAction7(MenuAction7 menuAction7) {
        MenuMain.menuAction7 = menuAction7;
    }

    @InjectValue(key = "MenuAction8")
    public static void setMenuAction8(MenuAction8 menuAction8) {
        MenuMain.menuAction8 = menuAction8;
    }

    @InjectValue(key = "MenuAction9")
    public static void setMenuAction9(MenuAction9 menuAction9) {
        MenuMain.menuAction9 = menuAction9;
    }

    @InjectValue(key = "MenuAction10")
    public static void setMenuAction10(MenuAction10 menuAction10) {
        MenuMain.menuAction10 = menuAction10;
    }

    @InjectValue(key = "MenuAction11")
    public static void setMenuAction11(MenuAction11 menuAction11) {
        MenuMain.menuAction11 = menuAction11;
    }

    @InjectValue(key = "MenuAction12")
    public static void setMenuAction12(MenuAction12 menuAction12) {
        MenuMain.menuAction12 = menuAction12;
    }

    @InjectValue(key = "MenuAction13")
    public static void setMenuAction13(MenuAction13 menuAction13) {
        MenuMain.menuAction13 = menuAction13;
    }

    @InjectValue(key = "MenuAction14")
    public static void setMenuAction14(MenuAction14 menuAction14) {
        MenuMain.menuAction14 = menuAction14;
    }

    @InjectValue(key = "MenuAction15")
    public static void setMenuAction15(MenuAction15 menuAction15) {
        MenuMain.menuAction15 = menuAction15;
    }

    @InjectValue(key = "MenuAction16")
    public static void setMenuAction16(MenuAction16 menuAction16) {
        MenuMain.menuAction16 = menuAction16;
    }

    @InjectValue(key = "MenuAction17")
    public static void setMenuAction17(MenuAction17 menuAction17) {
        MenuMain.menuAction17 = menuAction17;
    }

    @InjectValue(key = "MenuAction18")
    public static void setMenuAction18(MenuAction18 menuAction18) {
        MenuMain.menuAction18 = menuAction18;
    }

    @InjectValue(key = "MenuAction19")
    public static void setMenuAction19(MenuAction19 menuAction19) {
        MenuMain.menuAction19 = menuAction19;
    }

    @InjectValue(key = "SerializationService")
    public static void setSerializationService(SerializationService serializationService) {
        MenuMain.serializationService = serializationService;
    }

    @InjectValue(key = "InitializationService")
    public static void setInit(InitializationService initializationService) {
        MenuMain.initializationService = initializationService;
    }

    @InjectValue(key = "DatabaseService")
    public static void setDatabaseService(DatabaseService databaseService) {
        MenuMain.databaseService = databaseService;
    }

    public static void main(String[] args) {

        ContainerService.storeAllAnnotatedClassesToContainer();
        ContainerService.injectAnnotatedFields();
        PropertiesInjectionService.injectProperties();

        databaseService.databaseInitialize();
        databaseService.registerConnection();

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

