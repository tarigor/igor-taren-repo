package com.senla.betterthenspring;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScannerService {
    private static final Logger logger = LoggerFactory.getLogger(ScannerService.class);

    public static Set<Class<?>> classesScan() {
        List<Class<?>> classes = new ArrayList<>();
        try {
            classes = getAllClassesInMavenProject(System.getProperty("user.dir"));
        } catch (IOException | XmlPullParserException e) {
            logger.error("an error occurred during a class scan->" + e.getMessage());
            e.printStackTrace();
        }
        return new HashSet<>(classes);
    }

    public static List<Class<?>> getAllClassesInMavenProject(String projectDirectory) throws IOException, XmlPullParserException {
        List<Class<?>> classes = new ArrayList<>();
        File projectDir = new File(projectDirectory);
        if (!projectDir.exists() || !projectDir.isDirectory()) {
            throw new IllegalArgumentException("Invalid project directory: " + projectDirectory);
        }
        return traverseModules(projectDir, classes);
    }

    private static List<Class<?>> traverseModules(File directory, List<Class<?>> classes) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory() && new File(file, "pom.xml").exists()) {
                    String modulePath = file.getAbsolutePath();
                    classes.addAll(getClassesInModule(modulePath));
                    traverseModules(file, classes);
                }
            }
        }
        return classes;
    }

    private static List<Class<?>> getClassesInModule(String modulePath) {
        List<Class<?>> classes = new ArrayList<>();
        String sourceDirectory = modulePath + File.separator + "src" + File.separator + "main" + File.separator + "java";
        File sourceDir = new File(sourceDirectory);
        List<Class<?>> classesInModule = null;
        if (sourceDir.exists() && sourceDir.isDirectory()) {
            classesInModule = collectClasses(sourceDir, sourceDirectory, classes);
        }
        return classesInModule;
    }

    private static List<Class<?>> collectClasses(File directory, String sourceDirectory, List<Class<?>> classes) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    collectClasses(file, sourceDirectory, classes);
                } else if (file.isFile() && file.getName().endsWith(".java")) {
                    String filePath = file.getAbsolutePath();
                    String className = filePath.substring(sourceDirectory.length() + 1)
                            .replace(File.separatorChar, '.')
                            .replace(".java", "");
                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        logger.error("an error occurred during getting a class->" + e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return classes;
    }
}
