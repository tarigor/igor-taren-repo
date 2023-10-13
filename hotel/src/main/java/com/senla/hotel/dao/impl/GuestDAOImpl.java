package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Guest;
import com.senla.hoteldb.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestDAOImpl implements IEntityDAO<Guest> {
    protected HibernateService hibernateService;

    @InjectValue
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public List<Guest> getAll() {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            return session.createQuery("FROM Guest", Guest.class).list();
        }
    }

    @Override
    public Guest getById(long id) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            return session.get(Guest.class, id);
        }
    }

    @Override
    public void saveAll(List<Guest> guests) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            for (Guest guest : guests) {
                session.persist(guest);
            }
            transaction.commit();
        }
    }

    @Override
    public Guest update(Guest guest) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Guest updatedGuest = session.merge(guest);
            transaction.commit();
            return updatedGuest;
        }
    }

    @Override
    public void save(Guest guest) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(guest);
            transaction.commit();
        }
    }
}
