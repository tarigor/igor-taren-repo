package com.senla.betterthenspring.service;

import com.senla.betterthenspring.exception.BetterThanSpringModuleException;
import jakarta.persistence.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScannerService {
    public static final String TEXT = "java\\";
    public static final String JAVA_FILE_EXTENSION = ".java";
    public static final char DOT_CHAR = '.';
    public static final String EMPTY_CHAR = "";
    public static final String USER_DIR = "user.dir";
    private static final Logger logger = LoggerFactory.getLogger(ScannerService.class);

    public static HashSet<Class<?>> classesScan() throws BetterThanSpringModuleException {
        File projectRootDirectory = new File(System.getProperty(USER_DIR));
        return collectClasses(projectRootDirectory, new HashSet<>());
    }

    private static HashSet<Class<?>> collectClasses(File directory, HashSet<Class<?>> classes) throws BetterThanSpringModuleException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    collectClasses(file, classes);
                } else if (file.isFile() && file.getName().endsWith(JAVA_FILE_EXTENSION)) {
                    String filePath = file.getAbsolutePath();
                    String classPath = filePath.substring(filePath.indexOf(TEXT) + TEXT.length())
                            .replace(File.separatorChar, DOT_CHAR)
                            .replace(JAVA_FILE_EXTENSION, EMPTY_CHAR);
                    try {
                        classes.add(Class.forName(classPath));
                    } catch (ClassNotFoundException e) {
                        logger.error("an error occurred while loading class -> {}. Reason -> {}", classPath, e.getMessage());
                        throw new BetterThanSpringModuleException(e);
                    }
                }
            }
        } else {
            logger.error("No files found in provided directory");
            throw new BetterThanSpringModuleException("No files found in provided directory");
        }
        return classes;
    }

    public static List<Class<?>> getClassesEntityAnnotated(Set<Class<?>> classes) {
        List<Class<?>> entityClasses = new ArrayList<>();
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Entity.class)) {
                entityClasses.add(clazz);
            }
        }
        return entityClasses;
    }
}
