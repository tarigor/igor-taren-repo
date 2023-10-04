package com.senla.hoteldb;

import com.senla.container.ConfigProperty;
import com.senla.container.CreateInstanceAndPutInContainer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@CreateInstanceAndPutInContainer
public class DatabaseService {
    public static final String OS_NAME = "os.name";
    private static final String BATCH_FILE_PATH = "\\hotel-db\\sql\\db";
    private String jdbcDriver;
    private String dbUrl;
    private String user;
    private String password;

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

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void databaseInitialize(){
        String os = System.getProperty(OS_NAME).toLowerCase();
        try {
            Process process;
            int exitCode;
            if (os.contains("win")) {
                // Windows
               process = new ProcessBuilder("cmd.exe", "/c", System.getProperty("user.dir")  + BATCH_FILE_PATH + ".bat").start();
                System.out.println("here");
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // Linux or Unix
                process = new ProcessBuilder(System.getProperty("user.dir") + "\\" +BATCH_FILE_PATH + ".sh").start();
            } else {
                System.err.println("Unsupported operating system: " + os);
                return;
            }
            exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Batch file executed successfully.");
                System.out.println("here2");
            } else {
                System.err.println("Batch file execution failed with exit code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public DatabaseService registerConnection() {
        Connection connection;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(dbUrl, user, password);
            this.connection = connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //STEP 3: Execute a query
//            System.out.println("Creating table in given database...");
//            stmt = connection.createStatement();
//            String sql = "CREATE TABLE   REGISTRATION " +
//                    "(id INTEGER not NULL, " +
//                    " first VARCHAR(255), " +
//                    " last VARCHAR(255), " +
//                    " age INTEGER, " +
//                    " PRIMARY KEY ( id ))";
//            stmt.executeUpdate(sql);
//            System.out.println("Created table in given database...");

        // STEP 4: Clean-up environment
//            stmt.close();
//            connection.close();
//        } catch (SQLException se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        } catch (Exception e) {
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        } finally {
//            //finally block used to close resources
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException se2) {
//            } // nothing we can do
//            try {
//                if (connection != null) connection.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            } //end finally try
//        } //end try
        return this;
    }
}
