package com.senla.menu;

import com.senla.hotel.Hotel;
import com.senla.menu.action.impl.*;
import com.senla.menu.builder.Builder;
import com.senla.menu.controller.MenuController;
import com.senla.menu.entity.Menu;
import com.senla.menu.entity.MenuItem;
import com.senla.menu.navigator.Navigator;
import com.senla.menu.service.PropertiesService;

public class MenuMain {
    private static final Navigator NAVIGATOR = Navigator.getInstance();
    private static final PropertiesService PROPERTIES_SERVICE = PropertiesService.getInstance();
    private static final Builder BUILDER = Builder.getInstance();
    private static final MenuController MENU_CONTROLLER = MenuController.getInstance();
    private static String MENU_DESCRIPTION_FILENAME = "menu.properties";

    public static void main(String[] args) {

        Hotel.init();

        Menu menu = BUILDER
                .setTitle("HOTEL OPERATION")
                //        1=List of rooms sorted by price
                .addItem(new MenuItem(1, new MenuAction1()))
                //        2=List of rooms sorted by capacity
                .addItem(new MenuItem(2, new MenuAction2()))
                //        3=List of rooms sorted by number of stars
                .addItem(new MenuItem(3, new MenuAction3()))
                //        4=List of guests and their rooms sorted alphabetically
                .addItem(new MenuItem(4, new MenuAction4()))
                //        5=List of guests and their rooms sorted by check-out date
                .addItem(new MenuItem(5, new MenuAction5()))
                //        6=Total number of available rooms
                .addItem(new MenuItem(6, new MenuAction6()))
                //        7=Total number of guests
                .addItem(new MenuItem(7, new MenuAction7()))
                //        8=List of rooms that will be available on a certain date in the future
                .addItem(new MenuItem(8, new MenuAction8()))
                //        9=The amount of payment for the room to be paid by the guest
                .addItem(new MenuItem(9, new MenuAction9()))
                //        10=View the last 3 guests of the room and the dates of their stay
                .addItem(new MenuItem(10, new MenuAction10()))
                //        11=View the list of guest services and their price (sorted by PRICE,DATE) in ASC(DESC) manner
                .addItem(new MenuItem(11, new MenuAction11()))
                //        12=Prices of services and rooms (sorted by CAPACITY,PRICE,AVAILABILITY,SERVICE,RATING) in ASC(DESC) manner
                .addItem(new MenuItem(12, new MenuAction12()))
                //        13=Room services (ordered by ROME_SERVICES,PRICE) in ASC(DESC) manner
                .addItem(new MenuItem(13, new MenuAction13()))
                //        14=Show the details of a separate room
                .addItem(new MenuItem(14, new MenuAction14()))
                //        15=Import the certain entity from the CSV file
                .addItem(new MenuItem(15, new MenuAction15()))
                //        16=Export the certain entity
                .addItem(new MenuItem(16, new MenuAction16()))
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

