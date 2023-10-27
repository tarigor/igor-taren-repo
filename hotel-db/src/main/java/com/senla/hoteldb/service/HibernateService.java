package com.senla.hoteldb.service;


import com.senla.hoteldb.exception.HotelDbModuleException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HibernateService implements AutoCloseable {
    public static final String OS_NAME = "os.name";
    private static final Logger logger = LoggerFactory.getLogger(HibernateService.class);
    private static final String BATCH_FILE_PATH = "\\hotel-db\\sql\\db";
    private Session session;
    @Autowired
    private SessionFactory sessionFactory;

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
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logger.error("An error occurred during the transaction -> {}", e.getMessage());
        } catch (Exception e) {
            logger.error("An error occurred during the transaction -> {}", e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void close() {
        session.close();
    }

    public void databaseInitialize() throws HotelDbModuleException {
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
                logger.error("Unsupported operating system: {}", os);
                return;
            }
            exitCode = process.waitFor();
            if (exitCode == 0) {
                logger.info("Batch file executed successfully.");
            } else {
                logger.error("Batch file execution failed with exit code -> {}", exitCode);
            }
        } catch (IOException | InterruptedException e) {
            logger.error("{}:{}: an error occurred during IO operation -> {}", getClass().getName(), getClass().getEnclosingConstructor().getName(), e.getMessage());
            throw new HotelDbModuleException(e);
        }
    }
}
