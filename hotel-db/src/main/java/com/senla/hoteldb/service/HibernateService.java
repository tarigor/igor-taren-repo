package com.senla.hoteldb.service;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HibernateService implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(HibernateService.class);
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
}
