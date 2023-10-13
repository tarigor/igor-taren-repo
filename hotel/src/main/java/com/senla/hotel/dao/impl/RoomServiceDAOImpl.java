package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.RoomService;
import com.senla.hoteldb.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomServiceDAOImpl implements IEntityDAO<RoomService> {
    protected HibernateService hibernateService;

    @InjectValue
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public List<RoomService> getAll() {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            return session.createQuery("FROM RoomService", RoomService.class).list();
        }
    }

    @Override
    public RoomService getById(long id) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            return session.get(RoomService.class, id);
        }
    }

    @Override
    public void saveAll(List<RoomService> roomServices) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            for (RoomService roomService : roomServices) {
                session.persist(roomService);
            }
            transaction.commit();
        }
    }

    @Override
    public RoomService update(RoomService roomService) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            RoomService updatedRoomService = session.merge(roomService);
            transaction.commit();
            return updatedRoomService;
        }
    }

    @Override
    public void save(RoomService roomService) {
        try (Session session = hibernateService.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(roomService);
            transaction.commit();
        }
    }
}
