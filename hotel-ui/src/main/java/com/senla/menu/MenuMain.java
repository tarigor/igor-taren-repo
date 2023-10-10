package com.senla.menu;

import com.senla.betterthenspring.ContainerService;
import com.senla.betterthenspring.PropertiesInjectionService;
import com.senla.betterthenspring.ScannerService;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hoteldb.DatabaseService;
import com.senla.menu.service.MenuService;

import java.util.Set;

@CreateInstanceAndPutInContainer
public class MenuMain {
    private static DatabaseService databaseService;
    private static MenuService menuService;

    @InjectValue(key = "DatabaseService")
    public static void setDatabaseService(DatabaseService databaseService) {
        MenuMain.databaseService = databaseService;
    }

    @InjectValue(key = "MenuService")
    public static void setMenuService(MenuService menuService) {
        MenuMain.menuService = menuService;
    }

    public static void main(String[] args) {

        Set<Class<?>> scannedClasses = ScannerService.classesScan();

        ContainerService.storeAnnotatedInstanceInContainer(scannedClasses);
        ContainerService.injectValue(scannedClasses);
        PropertiesInjectionService.injectProperties(scannedClasses);

        databaseService.databaseInitialize();
        databaseService.registerConnection();

        menuService.showMenu();
    }
}

