package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Room;
import com.senla.hoteldb.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomDAOImpl implements IEntityDAO<Room> {
    private HibernateService hibernateService;

    @InjectValue
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public List<Room> getAll() {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            System.out.println("");
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
