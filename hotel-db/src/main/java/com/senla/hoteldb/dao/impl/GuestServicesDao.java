package com.senla.hoteldb.dao.impl;

import com.senla.hoteldb.dao.IEntityDao;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hoteldb.service.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuestServicesDao implements IEntityDao<GuestServices> {
    private HibernateService hibernateService;

    @Autowired
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public List<GuestServices> getAll() {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.createQuery("FROM GuestServices", GuestServices.class).list();
        }
        try (session) {
            return session.createQuery("FROM GuestServices", GuestServices.class).list();
        }
    }

    @Override
    public GuestServices getById(long id) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.get(GuestServices.class, id);
        }
        try (session) {
            return session.get(GuestServices.class, id);
        }
    }

    @Override
    public void saveAll(List<GuestServices> guestServices) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            for (GuestServices guestService : guestServices) {
                session.persist(guestService);
            }
        } else {
            try (session) {
                Transaction transaction = session.beginTransaction();
                for (GuestServices guestService : guestServices) {
                    session.persist(guestService);
                }
                transaction.commit();
            }
        }
    }

    @Override
    public GuestServices update(GuestServices guestServices) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.merge(guestServices);
        }
        try (session) {
            return session.merge(guestServices);
        }
    }

    @Override
    public void save(GuestServices guestServices) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            session.persist(guestServices);
        } else {
            try (session) {
                Transaction transaction = session.beginTransaction();
                session.persist(guestServices);
                transaction.commit();
            }
        }
    }
}
