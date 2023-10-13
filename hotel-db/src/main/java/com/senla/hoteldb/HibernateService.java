package com.senla.hoteldb;


import com.senla.container.ConfigProperty;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.RoomService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@CreateInstanceAndPutInContainer
public class HibernateService {
    private static final Logger logger = LoggerFactory.getLogger(HibernateService.class);
    private static final String BATCH_FILE_PATH = "\\hotel-db\\sql\\db";
    public static final String OS_NAME = "os.name";
    private SessionFactory sessionFactory;
    private String jdbcDriver;
    private String dbUrl;
    private String user;
    private String password;
    private String dialect;

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

    public void registerSession(){
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", jdbcDriver);
            configuration.setProperty("hibernate.connection.url", dbUrl);
            configuration.setProperty("hibernate.connection.username", user);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.dialect", dialect);
            configuration.addAnnotatedClass(Booking.class);
            configuration.addAnnotatedClass(Guest.class);
            configuration.addAnnotatedClass(GuestServices.class);
            configuration.addAnnotatedClass(Room.class);
            configuration.addAnnotatedClass(RoomService.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void databaseInitialize() {
        String os = System.getProperty(OS_NAME).toLowerCase();
        try {
            Process process;
            int exitCode;
            if (os.contains("win")) {
                //Windows
                String path = System.getProperty("user.dir") + BATCH_FILE_PATH + ".bat";
                process = new ProcessBuilder("cmd.exe", "/c", System.getProperty("user.dir") + BATCH_FILE_PATH + ".bat").start();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                //Linux
                process = new ProcessBuilder(System.getProperty("user.dir") + "\\" + BATCH_FILE_PATH + ".sh").start();
            } else {
                System.err.println("Unsupported operating system: " + os);
                return;
            }
            exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Batch file executed successfully.");
            } else {
                System.err.println("Batch file execution failed with exit code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            logger.error("an error occurred during IO operation->" + e.getMessage());
            e.printStackTrace();
        }
    }
}
