package com.senla.betterthenspring.service;

import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.betterthenspring.annotation.InjectValue;
import com.senla.betterthenspring.exception.BetterThanSpringModuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public class ContainerService {
    public static final String DOT = ".";
    public static final String TARGET = " arg0";
    public static final String REPLACEMENT = "";
    private static final Logger logger = LoggerFactory.getLogger(ContainerService.class);
    private static final HashMap<String, Object> instances = new HashMap<>();

    public static HashMap<String, Object> getInstances() {
        return instances;
    }

    public static void storeAnnotatedInstanceInContainer(Set<Class<?>> classes) throws BetterThanSpringModuleException {
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(CreateInstanceAndPutInContainer.class)) {
                String key = clazz.getSimpleName();
                try {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    Object instance = constructor.newInstance();
                    instances.put(key, instance);
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                         InvocationTargetException e) {
                    logger.error("an error occurred during storing instance of class in container -> {}", e.getMessage());
                    throw new BetterThanSpringModuleException("an error occurred during storing instance of class in container -> {}" + e.getMessage());
                }
            }
        }
        instances.forEach((k, v) -> logger.info("class name -> {} : {}", k, v));
    }

    public static void injectValue(Set<Class<?>> classes) throws BetterThanSpringModuleException {
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(CreateInstanceAndPutInContainer.class)) {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(InjectValue.class)) {
                        Object objectToInject = instances.get(getInjectingClassName(method));
                        if (objectToInject != null) {
                            Object o = instances.get(clazz.getSimpleName());
                            try {
                                method.invoke(o, objectToInject);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                logger.error("an error occurred during injecting of value -> {}", e.getMessage());
                                throw new BetterThanSpringModuleException("an error occurred during injecting of value -> " + e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    private static String getInjectingClassName(Method method) {
        String value = method.getParameters()[0].toString().replace(TARGET, REPLACEMENT);
        int lastIndex = value.lastIndexOf(DOT);
        return value.substring(lastIndex + 1);
    }
}
