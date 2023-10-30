package com.senla.menu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class PropertyFileReaderConfig {

    @Bean
    public Map<Integer, String> menuPropertyMap() throws IOException {
        Properties properties = new Properties();
        properties.load(new ClassPathResource("menu.properties").getInputStream());
        Map<Integer, String> propertyMap = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            propertyMap.put(Integer.valueOf(key), properties.getProperty(key));
        }
        return propertyMap;
    }
}
