package com.senla.betterthenspring;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScannerService {

    public static Set<Class<?>> classesScan() {
        List<Class<?>> classes = new ArrayList<>();
        try {
            classes = getAllClassesInMavenProject(System.getProperty("user.dir"));

            for (Class<?> clazz : classes) {
                System.out.println(clazz.getName());
            }
        } catch (IOException | XmlPullParserException e) {
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

    private static List<Class<?>> traverseModules(File directory, List<Class<?>> classes) throws IOException, XmlPullParserException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory() && new File(file, "pom.xml").exists()) {
                    String modulePath = file.getAbsolutePath();
                    classes.addAll(getClassesInModule(modulePath));
                    System.out.println("");
                    traverseModules(file, classes);
                }
            }
        }
        return classes;
    }

    private static List<Class<?>> getClassesInModule(String modulePath) throws IOException, XmlPullParserException {
        List<Class<?>> classes = new ArrayList<>();
        Model model = readPomFile(modulePath);
        String sourceDirectory = modulePath + File.separator + "src" + File.separator + "main" + File.separator + "java";
        File sourceDir = new File(sourceDirectory);
        List<Class<?>> classes1 = null;
        if (sourceDir.exists() && sourceDir.isDirectory()) {
            classes1 = collectClasses(sourceDir, sourceDirectory, classes);
        }
        return classes1;
    }

    private static List<Class<?>> collectClasses(File directory, String sourceDirectory, List<Class<?>> classes) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    collectClasses(file, sourceDirectory, classes);
                } else if (file.isFile() && file.getName().endsWith(".java") && !file.getName().contains("module-info")) {
                    String filePath = file.getAbsolutePath();
                    String className = filePath.substring(sourceDirectory.length() + 1)
                            .replace(File.separatorChar, '.')
                            .replace(".java", "");
                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return classes;
    }

    private static Model readPomFile(String pomFilePath) throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        FileReader fileReader = new FileReader(new File(pomFilePath, "pom.xml"));
        return reader.read(fileReader);
    }
}
