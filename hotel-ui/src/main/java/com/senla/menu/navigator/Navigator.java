package com.senla.menu.navigator;

import com.senla.menu.entity.Menu;
import com.senla.menu.service.PropertiesService;

import java.util.Map;

public class Navigator {

    private static final Navigator INSTANCE = new Navigator();
    private PropertiesService propertiesService;

    public static Navigator getInstance() {
        return INSTANCE;
    }

    public void setPropertiesService(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    public void navigate(Menu menu, String menuDescriptionFileName) {
        System.out.println(menu.getTitle());
        try {
            Map<Integer, String> menuDescription = propertiesService.readPropertiesFileAsMap(menuDescriptionFileName, "=");
            menu.getItems().keySet().forEach(k -> System.out.println(k + ". " + menuDescription.get(k)));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during the file reading with a filename -> " + menuDescriptionFileName
                    + "\n" + e.getMessage());
        }
    }
}
