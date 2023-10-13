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
    private HibernateService hibernateService;

    @InjectValue
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public List<Guest> getAll() {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.createQuery("FROM Guest", Guest.class).list();
        }
        try (session) {
            return session.createQuery("FROM Guest", Guest.class).list();
        }
    }

    @Override
    public Guest getById(long id) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.get(Guest.class, id);
        }
        try (session) {
            return session.get(Guest.class, id);
        }
    }

    @Override
    public void saveAll(List<Guest> guests) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            for (Guest guest : guests) {
                session.persist(guest);
            }
        } else {
            try (session) {
                Transaction transaction = session.beginTransaction();
                for (Guest guest : guests) {
                    session.persist(guest);
                }
                transaction.commit();
            }
        }
    }

    @Override
    public Guest update(Guest guest) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.merge(guest);
        }
        try (session) {
            return session.merge(guest);
        }
    }

    @Override
    public void save(Guest guest) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            session.persist(guest);
        } else {
            try (session) {
                session.persist(guest);
            }
        }
    }
}
