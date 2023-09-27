package com.senla.hotel.service.impl;

import com.senla.container.CreateInstanceAndPutInContainer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@CreateInstanceAndPutInContainer
public class PropertyFileServiceImpl {

    public String getSettingFromPropertiesFile(String propertiesFileName, String settingName) {
        String settingValue = "";
        String PATH = "\\hotel\\resources";
        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + PATH + "\\" + propertiesFileName + ".properties")) {
            Properties prop = new Properties();
            prop.load(input);
            settingValue = prop.getProperty(settingName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return settingValue;
    }
}
