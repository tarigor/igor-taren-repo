package com.senla.menu;

import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.betterthenspring.annotation.InjectValue;
import com.senla.betterthenspring.exception.BetterThanSpringModuleException;
import com.senla.betterthenspring.service.ContainerService;
import com.senla.betterthenspring.service.PropertiesInjectionService;
import com.senla.betterthenspring.service.ScannerService;
import com.senla.hoteldb.exception.HotelDbModuleException;
import com.senla.hoteldb.service.HibernateService;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import com.senla.menu.exception.HotelUiModuleException;
import com.senla.menu.service.MenuService;
import com.serialization.exception.HotelSerializationModuleException;
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
        try {
            Set<Class<?>> scannedClasses = ScannerService.classesScan();
            ContainerService.storeAnnotatedInstanceInContainer(scannedClasses);
            ContainerService.injectValue(scannedClasses);
            PropertiesInjectionService.injectProperties(scannedClasses);
            hibernateService.databaseInitialize();
            List<Class<?>> classesEntityAnnotated = ScannerService.getClassesEntityAnnotated(scannedClasses);
            hibernateService.registerSession(classesEntityAnnotated);
            menuService.showMenu();
        } catch (HotelUiModuleException e) {
            logger.error("An error occurred in hotel-ui module -> {}", e.getMessage());
        } catch (BetterThanSpringModuleException e) {
            logger.error("An error occurred in betterthanspring module -> {}", e.getMessage());
        } catch (HotelDbModuleException e) {
            logger.error("An error occurred in hotel-db module -> {}", e.getMessage());
        } catch (HotelSerializationModuleException e) {
            logger.error("An error occurred in Hotel-serialization module -> {}", e.getMessage());
        } catch (HotelIoModuleException e) {
            logger.error("An error occurred in Hotel-io -> {}", e.getMessage());
        }
    }
}

