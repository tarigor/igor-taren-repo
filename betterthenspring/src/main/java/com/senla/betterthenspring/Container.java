package com.senla.betterthenspring;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public class Container {
    private static final HashMap<String, Object> instances = new HashMap<>();

    private static void storeAnnotatedInstanceInContainer(Set<Class<?>> classesToInspect) {
        for (Class<?> clazz : classesToInspect) {
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
                    e.printStackTrace();
                }
            }
        }
        instances.forEach((k, v) -> System.out.println("class name->" + k + " object->" + v));
    }

    public static void injectValue() {
        for (Module m : ModuleLayer.boot().modules()) {
            if (m.getName().contains("hotel"))
                for (String p : m.getPackages()) {
                    try {
                        // Get all classes in the package
                        Class<?>[] classes = Scanner.classesScan().toArray(new Class[0]);
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
                                            method.invoke(o, objectToInject);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        // Handle class loading errors
                        System.out.println("error -> " + e);
                        e.printStackTrace();
                    }
                }
        }
    }

    public static void injectAnnotatedFields() {
        try {
            injectValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void storeAllAnnotatedClassesToContainer() {
        Set<Class<?>> scannedClasses = Scanner.classesScan();
        storeAnnotatedInstanceInContainer(scannedClasses);
    }
}
