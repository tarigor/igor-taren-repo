package com.senla.hoteldb.dao.impl;

import com.senla.hoteldb.dao.IEntityDao;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.service.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingDao implements IEntityDao<Booking> {
    private HibernateService hibernateService;

    @Autowired
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public List<Booking> getAll() {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.createQuery("FROM Booking", Booking.class).list();
        }
        try (session) {
            return session.createQuery("FROM Booking", Booking.class).list();
        }
    }

    @Override
    public Booking getById(long id) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.get(Booking.class, id);
        }
        try (session) {
            return session.get(Booking.class, id);
        }
    }

    @Override
    public void saveAll(List<Booking> bookings) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            for (Booking booking : bookings) {
                session.persist(booking);
            }
        }
        try (session) {
            Transaction transaction = session.beginTransaction();
            for (Booking booking : bookings) {
                session.persist(booking);
            }
            transaction.commit();
        }
    }

    @Override
    public Booking update(Booking booking) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.merge(booking);
        }
        try (session) {
            return session.merge(booking);
        }
    }

    @Override
    public void save(Booking booking) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            session.persist(booking);
        }
        try (session) {
            session.persist(booking);
        }
    }
}
