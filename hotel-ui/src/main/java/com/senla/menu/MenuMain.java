package com.senla.menu;

import com.senla.hotel.Hotel;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.menu.action.impl.*;
import com.senla.menu.builder.Builder;
import com.senla.menu.controller.MenuController;
import com.senla.menu.entity.Menu;
import com.senla.menu.entity.MenuItem;
import com.senla.menu.navigator.Navigator;
import com.senla.menu.service.PropertiesService;

public class MenuMain {

    private static final BookingServiceImpl BOOKING_SERVICE = BookingServiceImpl.getInstance();
    private static final GuestServicesServiceImpl GUEST_SERVICES_SERVICE = GuestServicesServiceImpl.getInstance();
    private static final RoomServiceImpl ROOM_SERVICE = RoomServiceImpl.getInstance();
    private static final RoomServicesServiceImpl ROOM_SERVICES_SERVICE = RoomServicesServiceImpl.getInstance();
    private static final Navigator NAVIGATOR = Navigator.getInstance();
    private static final PropertiesService PROPERTIES_SERVICE = PropertiesService.getInstance();
    private static final Builder BUILDER = Builder.getInstance();
    private static final MenuController MENU_CONTROLLER = MenuController.getInstance();
    private static String MENU_DESCRIPTION_FILENAME = "menu.properties";

    public static void main(String[] args) {

        Hotel.init();

        Menu menu = BUILDER
                .setTitle("HOTEL OPERATION")
                .addItem(new MenuItem(1, new MenuAction1(BOOKING_SERVICE)))
                .addItem(new MenuItem(2, new MenuAction2()))
                .addItem(new MenuItem(3, new MenuAction3()))
                .addItem(new MenuItem(4, new MenuAction4()))
                .addItem(new MenuItem(5, new MenuAction5()))
                .addItem(new MenuItem(6, new MenuAction6()))
                .addItem(new MenuItem(7, new MenuAction7()))
                .addItem(new MenuItem(8, new MenuAction8()))
                .addItem(new MenuItem(9, new MenuAction9()))
                .addItem(new MenuItem(10, new MenuAction10()))
                .addItem(new MenuItem(11, new MenuAction11()))
                .addItem(new MenuItem(12, new MenuAction12()))
                .addItem(new MenuItem(13, new MenuAction13()))
                .addItem(new MenuItem(14, new MenuAction14()))
                .build();

        NAVIGATOR.setPropertiesService(PROPERTIES_SERVICE);

        MENU_CONTROLLER.setMenu(menu);
        MENU_CONTROLLER.setNavigator(NAVIGATOR);
        MENU_CONTROLLER.setMenuDescriptionFileName(MENU_DESCRIPTION_FILENAME);
        MENU_CONTROLLER.setBuilder(BUILDER);
        MENU_CONTROLLER
                .showMenu()
                .menuRolling();
    }
}

