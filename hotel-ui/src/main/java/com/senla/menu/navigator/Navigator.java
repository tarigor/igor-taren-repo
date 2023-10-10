package com.senla.menu.navigator;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.menu.entity.Menu;
import com.senla.menu.service.PropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@CreateInstanceAndPutInContainer
public class Navigator {
    private static final Logger logger = LoggerFactory.getLogger(Navigator.class);
    private PropertiesService propertiesService;

    public void setPropertiesService(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    public void navigate(Menu menu, String menuDescriptionFileName) {
        System.out.println(menu.getTitle());
        try {
            Map<Integer, String> menuDescription = propertiesService.readPropertiesFileAsMap(menuDescriptionFileName, "=");
            menu.getItems().keySet().forEach(k -> System.out.println(k + ". " + menuDescription.get(k)));
        } catch (Exception e) {
            logger.error("an error occurred during during access to properties file->" + e.getMessage());
            throw new RuntimeException("An error occurred during the file reading with a filename -> " + menuDescriptionFileName
                    + "\n" + e.getMessage());
        }
    }
}
