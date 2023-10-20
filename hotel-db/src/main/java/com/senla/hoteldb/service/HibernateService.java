package com.senla.hoteldb.service;


import com.senla.container.ConfigProperty;
import com.senla.container.CreateInstanceAndPutInContainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@CreateInstanceAndPutInContainer
public class HibernateService implements AutoCloseable {
    public static final String OS_NAME = "os.name";
    private static final Logger logger = LoggerFactory.getLogger(HibernateService.class);
    private static final String BATCH_FILE_PATH = "\\hotel-db\\sql\\db";
    private SessionFactory sessionFactory;
    private String jdbcDriver;
    private String dbUrl;
    private String user;
    private String password;
    private String dialect;

    private Session session;

    @ConfigProperty(moduleName = "hotel-db", propertiesFileName = "database", parameterName = "jdbcDriver", type = String.class)
    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    @ConfigProperty(moduleName = "hotel-db", propertiesFileName = "database", parameterName = "dbUrl", type = String.class)
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @ConfigProperty(moduleName = "hotel-db", propertiesFileName = "database", parameterName = "user", type = String.class)
    public void setUser(String user) {
        this.user = user;
    }

    @ConfigProperty(moduleName = "hotel-db", propertiesFileName = "database", parameterName = "password", type = String.class)
    public void setPassword(String password) {
        this.password = password;
    }

    @ConfigProperty(moduleName = "hotel-db", propertiesFileName = "database", parameterName = "dialect", type = String.class)
    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public void registerSession(List<Class<?>> clazz) {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", jdbcDriver);
            configuration.setProperty("hibernate.connection.url", dbUrl);
            configuration.setProperty("hibernate.connection.username", user);
            configuration.setProperty("hibernate.connection.password", password);
            for (Class<?> s : clazz) {
                configuration.addAnnotatedClass(s);
            }
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            logger.error("An error occurred during session register -> " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Session getSession() {
        if (session == null || !session.isOpen()) {
            this.session = sessionFactory.openSession();
        }
        return session;
    }

    public void beginTransaction() {
        getSession();
        session.beginTransaction();
    }

    public void commit() {
        try {
            session.getTransaction().commit();
            close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            close();
            logger.error("An error occurred during the transaction -> " + e.getMessage());
        }
    }

    @Override
    public void close() {
        session.close();
    }

    public void databaseInitialize() {
        String os = System.getProperty(OS_NAME).toLowerCase();
        try {
            Process process;
            int exitCode;
            if (os.contains("win")) {
                //Windows
                process = new ProcessBuilder("cmd.exe", "/c", System.getProperty("user.dir") + BATCH_FILE_PATH + ".bat").start();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                //Linux
                process = new ProcessBuilder(System.getProperty("user.dir") + "\\" + BATCH_FILE_PATH + ".sh").start();
            } else {
                logger.error("Unsupported operating system: " + os);
                return;
            }
            exitCode = process.waitFor();
            if (exitCode == 0) {
                logger.info("Batch file executed successfully.");
            } else {
                logger.error("Batch file execution failed with exit code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            logger.error("an error occurred during IO operation->" + e.getMessage());
            e.printStackTrace();
        }
    }
}
