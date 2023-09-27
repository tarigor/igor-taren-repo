package com.senla.betterthenspring;

import com.senla.container.ConfigProperty;
import com.senla.container.CreateInstanceAndPutInContainer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Properties;

@CreateInstanceAndPutInContainer
public class PropertiesInjectionService {
    private static final Properties properties = new Properties();

    public static void injectProperties() {
        for (Module m : ModuleLayer.boot().modules()) {
            if (m.getName().contains("hotel"))
                for (String p : m.getPackages()) {
                    try {
                        // Get all classes in the package
                        Class<?>[] classes = ScannerService.classesScan().toArray(new Class[0]);
                        for (Class<?> clazz : classes) {
                            if (clazz.isAnnotationPresent(CreateInstanceAndPutInContainer.class)) {
                                Method[] methods = clazz.getDeclaredMethods();
                                for (Method method : methods) {
                                    if (method.isAnnotationPresent(ConfigProperty.class)) {
                                        Annotation annotation = method.getAnnotation(ConfigProperty.class);

                                        String propertiesFileName = ((ConfigProperty) annotation).propertiesFileName();
                                        String parameterName = ((ConfigProperty) annotation).parameterName();
                                        Class<?> parameterType = ((ConfigProperty) annotation).type();

                                        Object o = ContainerService.getInstances().get(clazz.getSimpleName());
                                        method.invoke(o, getSettingFromPropertiesFile(propertiesFileName, parameterName, parameterType));

                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("error -> " + e);
                        e.printStackTrace();
                    }
                }
        }
    }

    private static <T> Object getSettingFromPropertiesFile(String propertiesFileName, String settingName, Class<T> parameterType) {
        String settingValue = "";
        String PATH = "\\hotel\\resources";
        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + PATH + "\\" + propertiesFileName + ".properties")) {
            properties.load(input);
            settingValue = properties.getProperty(settingName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (settingValue == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        if (settingValue == null) {
            throw new IllegalArgumentException("Wrapper class is null");
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
}
