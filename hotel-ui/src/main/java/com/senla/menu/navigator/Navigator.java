package com.senla.menu.navigator;

import com.senla.menu.entity.Menu;
import com.senla.menu.exception.HotelUiModuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Navigator {
    private static final Logger logger = LoggerFactory.getLogger(Navigator.class);
    private final Map<Integer, String> menuPropertyMap;

    @Autowired
    public Navigator(Map<Integer, String> menuPropertyMap) {
        this.menuPropertyMap = menuPropertyMap;
    }

    public void navigate(Menu menu) throws HotelUiModuleException {
        System.out.println(menu.getTitle());
        try {
            menuPropertyMap.keySet().forEach(k -> System.out.println(k + ". " + menuPropertyMap.get(k)));
        } catch (Exception e) {
            logger.error("an error occurred during during access to properties file -> {}", e.getMessage());
            throw new HotelUiModuleException(e);
        }
    }
}
