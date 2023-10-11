package com.senla.betterthenspring;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public class ContainerService {
    private static final Logger logger = LoggerFactory.getLogger(ContainerService.class);
    private static HashMap<String, Object> instances = new HashMap<>();

    public static HashMap<String, Object> getInstances() {
        return instances;
    }

    public static void storeAnnotatedInstanceInContainer(Set<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(CreateInstanceAndPutInContainer.class)) {
                System.out.println("detected class with field annotation->" + clazz);
                String key = clazz.getSimpleName();
                try {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    Object instance = constructor.newInstance();
                    instances.put(key, instance);
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                         InvocationTargetException e) {
                    logger.error("an error occurred during storing instance of class in container->" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        instances.forEach((k, v) -> System.out.println("class name->" + k + " object->" + v));
    }

    public static void injectValue(Set<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(CreateInstanceAndPutInContainer.class)) {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(InjectValue.class)) {
                        Annotation annotation = method.getAnnotation(InjectValue.class);
                        String key = ((InjectValue) annotation).key();
                        Object objectToInject = instances.get(key);
                        if (objectToInject != null) {
                            Object o = instances.get(clazz.getSimpleName());
                            try {
                                method.invoke(o, objectToInject);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                logger.error("an error occurred during injecting of value->" + e.getMessage());
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }
    }
}
