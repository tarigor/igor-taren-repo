package com.senla.hoteldb.dao.impl;

import com.senla.hoteldb.dao.IEntityDao;
import com.senla.hoteldb.entity.Room;
import com.senla.hoteldb.service.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomDao implements IEntityDao<Room> {
    private HibernateService hibernateService;

    @Autowired
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public List<Room> getAll() {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.createQuery("FROM Room", Room.class).list();
        }
        try (session) {
            return session.createQuery("FROM Room", Room.class).list();
        }
    }

    @Override
    public Room getById(long id) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.get(Room.class, id);
        }
        try (session) {
            return session.get(Room.class, id);
        }
    }

    @Override
    public void saveAll(List<Room> rooms) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            for (Room room : rooms) {
                session.persist(room);
            }
        } else {
            try (session) {
                Transaction transaction = session.beginTransaction();
                for (Room room : rooms) {
                    session.persist(room);
                }
                transaction.commit();
            }
        }
    }

    @Override
    public Room update(Room room) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.merge(room);
        }
        try (session) {
            return session.merge(room);
        }
    }

    @Override
    public void save(Room room) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            session.persist(room);
        } else {
            try (session) {
                session.persist(room);
            }
        }
    }
}
