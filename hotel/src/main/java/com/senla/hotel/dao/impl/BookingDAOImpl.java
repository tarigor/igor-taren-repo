package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Booking;
import com.senla.hoteldb.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@CreateInstanceAndPutInContainer
public class BookingDAOImpl implements IEntityDAO<Booking> {
    protected HibernateService hibernateService;

    @InjectValue
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public List<Booking> getAll() {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            return session.createQuery("FROM Booking", Booking.class).list();
        }
    }

    @Override
    public Booking getById(long id) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            return session.get(Booking.class, id);
        }
    }

    @Override
    public void saveAll(List<Booking> bookings) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            for (Booking booking : bookings) {
                session.persist(booking);
            }
            transaction.commit();
        }
    }

    @Override
    public Booking update(Booking booking) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Booking updatedBooking = session.merge(booking);
            transaction.commit();
            return updatedBooking;
        }
    }

    @Override
    public void save(Booking booking) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(booking);
            transaction.commit();
        }
    }
}
