package com.senla.betterthenspring;

import com.senla.container.ConfigProperty;
import com.senla.container.CreateInstanceAndPutInContainer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

@CreateInstanceAndPutInContainer
public class PropertiesInjectionService {
    static HashMap<String, Properties> propertiesHashMap = new HashMap<>();

    public static void injectProperties(Set<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(CreateInstanceAndPutInContainer.class)) {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(ConfigProperty.class)) {
                        Annotation annotation = method.getAnnotation(ConfigProperty.class);

                        String moduleName = ((ConfigProperty) annotation).moduleName();
                        String propertiesFileName = ((ConfigProperty) annotation).propertiesFileName();
                        String parameterName = ((ConfigProperty) annotation).parameterName();
                        Class<?> parameterType = ((ConfigProperty) annotation).type();

                        Properties propertiesFromContainer = loadProperties(moduleName, propertiesFileName);

                        Object o = ContainerService.getInstances().get(clazz.getSimpleName());
                        try {
                            method.invoke(o, getSettingFromPropertiesFile(propertiesFromContainer, parameterName, parameterType));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    private static <T> Object getSettingFromPropertiesFile(Properties properties, String settingName, Class<T> parameterType) {
        String settingValue = properties.getProperty(settingName);
        if (settingValue == null) {
            throw new IllegalArgumentException("Input string is null");
        }
        String stringValue = settingValue.trim(); // Remove leading/trailing whitespace
        try {
            if (parameterType == Integer.class) {
                return (T) Integer.valueOf(stringValue);
            } else if (parameterType == Double.class) {
                return (T) Double.valueOf(stringValue);
            } else if (parameterType == Boolean.class) {
                return (T) Boolean.valueOf(stringValue);
            } else if (parameterType == Long.class) {
                return (T) Long.valueOf(stringValue);
            } else if (parameterType == String.class) {
                return (T) settingValue;
            } else if (parameterType == Character.class) {
                if (stringValue.length() != 1) {
                    throw new IllegalArgumentException("Input string should have exactly one character");
                }
                return (T) Character.valueOf(stringValue.charAt(0));
            } else {
                throw new IllegalArgumentException("Unsupported wrapper class");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input string format for " + parameterType.getSimpleName(), e);
        }
    }

    private static Properties loadProperties(String moduleName, String propertiesFileName) {
        if (propertiesHashMap.get(propertiesFileName) == null) {
            String PATH = "\\" + moduleName + "\\src\\main\\resources";
            Properties properties = new Properties();
            try (InputStream input = new FileInputStream(System.getProperty("user.dir") + PATH + "\\" + propertiesFileName + ".properties")) {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            propertiesHashMap.put(propertiesFileName, properties);
            return properties;
        } else {
            return propertiesHashMap.get(propertiesFileName);
        }
    }
}
