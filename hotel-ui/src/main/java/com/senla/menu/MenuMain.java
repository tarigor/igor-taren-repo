package com.senla.menu;

import com.senla.hotel.exception.HotelModuleException;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import com.senla.menu.exception.HotelUiModuleException;
import com.senla.menu.service.MenuService;
import com.senla.serialization.exception.HotelSerializationModuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Locale;

@SpringBootApplication
@ComponentScan(basePackages = {"com.senla.hotel", "com.senla.hoteldb", "com.senla.hotelio", "com.senla.serialization", "com.senla.menu"})
@EnableJpaRepositories("com.senla.hoteldb.repository")
@EntityScan(basePackages = {"com.senla.hoteldb.entity"})
public class MenuMain {
    private static final Logger logger = LoggerFactory.getLogger(MenuMain.class);

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(MenuMain.class, args);

        MenuService menuService = ctx.getBean(MenuService.class);

        Locale.setDefault(Locale.US);

        try {
            menuService.showMenu();
        } catch (HotelUiModuleException e) {
            logger.error("An error occurred in hotel-ui module -> {}", e.getMessage());
        } catch (HotelSerializationModuleException e) {
            logger.error("An error occurred in hotel-serialization module -> {}", e.getMessage());
        } catch (HotelIoModuleException e) {
            logger.error("An error occurred in hotel-io module -> {}", e.getMessage());
        } catch (HotelModuleException e) {
            logger.error("An error occurred in hotel module -> {}", e.getMessage());
        }
    }
}

