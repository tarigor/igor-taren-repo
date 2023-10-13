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
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            return session.createQuery("FROM Room", Room.class).list();
        }
    }

    @Override
    public Room getById(long id) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            return session.get(Room.class, id);
        }
    }

    @Override
    public void saveAll(List<Room> rooms) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            for (Room room : rooms) {
                session.persist(room);
            }
            transaction.commit();
        }
    }

    @Override
    public Room update(Room room) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Room updatedRoom = session.merge(room);
            transaction.commit();
            return updatedRoom;
        }
    }

    @Override
    public void save(Room room) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(room);
            transaction.commit();
        }
    }
}
