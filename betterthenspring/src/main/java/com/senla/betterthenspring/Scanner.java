package com.senla.betterthenspring;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Scanner {

    public static Set<Class<?>> classesScan() {
        Set<Class<?>> classesToInspect = new HashSet<>();
        for (Module m : ModuleLayer.boot().modules()) {
            if (m.getName().contains("hotel"))
                for (String p : m.getPackages()) {
                    classesToInspect.addAll(getClassesInPackage(p));
                }
        }
        return classesToInspect;
    }

    private static List<Class<?>> getClassesInPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String packagePath = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File packageDirectory = new File(classLoader.getResource(packagePath).getFile());

        if (packageDirectory.exists() && packageDirectory.isDirectory()) {
            for (File file : packageDirectory.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".class")) {
                    String className = packageName + "." + file.getName().replaceAll("\\.class$", "");

                    try {
                        Class<?> clazz = Class.forName(className);
                        classes.add(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return classes;
    }
}
