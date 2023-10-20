package com.senla.menu.service;

import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.betterthenspring.annotation.InjectValue;
import com.senla.menu.action.impl.MenuAction1;
import com.senla.menu.action.impl.MenuAction10;
import com.senla.menu.action.impl.MenuAction11;
import com.senla.menu.action.impl.MenuAction12;
import com.senla.menu.action.impl.MenuAction13;
import com.senla.menu.action.impl.MenuAction14;
import com.senla.menu.action.impl.MenuAction15;
import com.senla.menu.action.impl.MenuAction16;
import com.senla.menu.action.impl.MenuAction17;
import com.senla.menu.action.impl.MenuAction18;
import com.senla.menu.action.impl.MenuAction19;
import com.senla.menu.action.impl.MenuAction2;
import com.senla.menu.action.impl.MenuAction3;
import com.senla.menu.action.impl.MenuAction4;
import com.senla.menu.action.impl.MenuAction5;
import com.senla.menu.action.impl.MenuAction6;
import com.senla.menu.action.impl.MenuAction7;
import com.senla.menu.action.impl.MenuAction8;
import com.senla.menu.action.impl.MenuAction9;
import com.senla.menu.builder.Builder;
import com.senla.menu.controller.MenuController;
import com.senla.menu.entity.Menu;
import com.senla.menu.entity.MenuItem;
import com.senla.menu.exception.CommonExceptionHotelUIModule;
import com.senla.menu.navigator.Navigator;

@CreateInstanceAndPutInContainer
public class MenuService {
    private String MENU_DESCRIPTION_FILENAME = "\\hotel-ui\\src\\main\\resources\\menu.properties";
    private MenuController MENU_CONTROLLER;
    private Builder builder;
    private PropertiesService PROPERTIES_SERVICE;
    private Navigator navigator;
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

    @InjectValue
    public void setMenuController(MenuController menuController) {
        this.MENU_CONTROLLER = menuController;
    }

    @InjectValue
    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    @InjectValue
    public void setPropertiesService(PropertiesService propertiesService) {
        this.PROPERTIES_SERVICE = propertiesService;
    }

    @InjectValue
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    @InjectValue
    public void setMenuAction1(MenuAction1 menuAction1) {
        this.menuAction1 = menuAction1;
    }

    @InjectValue
    public void setMenuAction2(MenuAction2 menuAction2) {
        this.menuAction2 = menuAction2;
    }

    @InjectValue
    public void setMenuAction3(MenuAction3 menuAction3) {
        this.menuAction3 = menuAction3;
    }

    @InjectValue
    public void setMenuAction4(MenuAction4 menuAction4) {
        this.menuAction4 = menuAction4;
    }

    @InjectValue
    public void setMenuAction5(MenuAction5 menuAction5) {
        this.menuAction5 = menuAction5;
    }

    @InjectValue
    public void setMenuAction6(MenuAction6 menuAction6) {
        this.menuAction6 = menuAction6;
    }

    @InjectValue
    public void setMenuAction7(MenuAction7 menuAction7) {
        this.menuAction7 = menuAction7;
    }

    @InjectValue
    public void setMenuAction8(MenuAction8 menuAction8) {
        this.menuAction8 = menuAction8;
    }

    @InjectValue
    public void setMenuAction9(MenuAction9 menuAction9) {
        this.menuAction9 = menuAction9;
    }

    @InjectValue
    public void setMenuAction10(MenuAction10 menuAction10) {
        this.menuAction10 = menuAction10;
    }

    @InjectValue
    public void setMenuAction11(MenuAction11 menuAction11) {
        this.menuAction11 = menuAction11;
    }

    @InjectValue
    public void setMenuAction12(MenuAction12 menuAction12) {
        this.menuAction12 = menuAction12;
    }

    @InjectValue
    public void setMenuAction13(MenuAction13 menuAction13) {
        this.menuAction13 = menuAction13;
    }

    @InjectValue
    public void setMenuAction14(MenuAction14 menuAction14) {
        this.menuAction14 = menuAction14;
    }

    @InjectValue
    public void setMenuAction15(MenuAction15 menuAction15) {
        this.menuAction15 = menuAction15;
    }

    @InjectValue
    public void setMenuAction16(MenuAction16 menuAction16) {
        this.menuAction16 = menuAction16;
    }

    @InjectValue
    public void setMenuAction17(MenuAction17 menuAction17) {
        this.menuAction17 = menuAction17;
    }

    @InjectValue
    public void setMenuAction18(MenuAction18 menuAction18) {
        this.menuAction18 = menuAction18;
    }

    @InjectValue
    public void setMenuAction19(MenuAction19 menuAction19) {
        this.menuAction19 = menuAction19;
    }

    public void showMenu() throws CommonExceptionHotelUIModule {
        Menu menu = builder
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


        navigator.setPropertiesService(PROPERTIES_SERVICE);

        MENU_CONTROLLER.setMenu(menu);
        MENU_CONTROLLER.setNavigator(navigator);
        MENU_CONTROLLER.setMenuDescriptionFileName(MENU_DESCRIPTION_FILENAME);
        MENU_CONTROLLER.setBuilder(builder);
        MENU_CONTROLLER.showMenu()
                .menuRolling();
    }
}
