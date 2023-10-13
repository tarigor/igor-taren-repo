package com.senla.menu;

import com.senla.betterthenspring.ContainerService;
import com.senla.betterthenspring.PropertiesInjectionService;
import com.senla.betterthenspring.ScannerService;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hoteldb.HibernateService;
import com.senla.menu.service.MenuService;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@CreateInstanceAndPutInContainer
public class MenuMain {
    private static MenuService menuService;

    private static HibernateService hibernateService;

    @InjectValue
    public static void setHibernateService(HibernateService hibernateService) {
        MenuMain.hibernateService = hibernateService;
    }

    @InjectValue
    public static void setMenuService(MenuService menuService) {
        MenuMain.menuService = menuService;
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Set<Class<?>> scannedClasses = ScannerService.classesScan();

        ContainerService.storeAnnotatedInstanceInContainer(scannedClasses);
        ContainerService.injectValue(scannedClasses);
        PropertiesInjectionService.injectProperties(scannedClasses);

        hibernateService.databaseInitialize();

        List<Class<?>> classesEntityAnnotated = ScannerService.getClassesEntityAnnotated(scannedClasses);
        hibernateService.registerSession(classesEntityAnnotated);

        menuService.showMenu();
    }
}

