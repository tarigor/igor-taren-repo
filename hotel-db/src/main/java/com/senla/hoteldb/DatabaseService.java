package com.senla.hoteldb;

import com.senla.container.ConfigProperty;
import com.senla.container.CreateInstanceAndPutInContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@CreateInstanceAndPutInContainer
public class DatabaseService {
    public static final String OS_NAME = "os.name";
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    private static final String BATCH_FILE_PATH = "\\hotel-db\\sql\\db";
    private String jdbcDriver;
    private String dbUrl;
    private String user;
    private String password;
    private Connection connection;

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

    public Connection getConnection() {
        try {
            if (connection.isClosed() || connection == null) {
                registerConnection();
            }
        } catch (SQLException e) {
            logger.error("an error occurred during SQL operation->" + e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
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

    public void registerConnection() {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("an error occurred during SQL operation->" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void setAutocommit(Boolean state) {
        try {
            connection.setAutoCommit(state);
        } catch (SQLException e) {
            logger.error("an error occurred during SQL operation->" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                logger.error("an error occurred during SQL operation->" + e.getMessage());
                rollbackException.printStackTrace();
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeException) {
                    logger.error("an error occurred during SQL operation->" + closeException.getMessage());
                    closeException.printStackTrace();
                }
            }
        }
    }
}
