package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.GuestServices;
import com.senla.hoteldb.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestServicesDAOImpl implements IEntityDAO<GuestServices> {
    protected HibernateService hibernateService;

    @InjectValue
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public List<GuestServices> getAll() {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            return session.createQuery("FROM GuestServices", GuestServices.class).list();
        }
    }

    @Override
    public GuestServices getById(long id) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            return session.get(GuestServices.class, id);
        }
    }

    @Override
    public void saveAll(List<GuestServices> guestServices) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            for (GuestServices guestService : guestServices) {
                session.persist(guestService);
            }
            transaction.commit();
        }
    }

    @Override
    public GuestServices update(GuestServices guestServices) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            GuestServices updatedGuestServices = session.merge(guestServices);
            transaction.commit();
            return updatedGuestServices;
        }
    }

    @Override
    public void save(GuestServices guestServices) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(guestServices);
            transaction.commit();
        }
    }
}
