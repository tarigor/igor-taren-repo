package com.senla.menu;

import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.betterthenspring.annotation.InjectValue;
import com.senla.betterthenspring.service.ContainerService;
import com.senla.betterthenspring.service.PropertiesInjectionService;
import com.senla.betterthenspring.service.ScannerService;
import com.senla.hoteldb.service.HibernateService;
import com.senla.menu.exception.CommonExceptionHotelUIModule;
import com.senla.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@CreateInstanceAndPutInContainer
public class MenuMain {
    private static final Logger logger = LoggerFactory.getLogger(MenuMain.class);

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

        try {
            menuService.showMenu();
        } catch (CommonExceptionHotelUIModule e) {
            logger.error("An error occurred -> {}", e.getMessage());
            throw new RuntimeException("An error occurred -> " + e.getMessage());
        }
    }
}

