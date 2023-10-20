package com.senla.hoteldb.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hoteldb.dao.IEntityDAO;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hoteldb.service.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomServiceDAO implements IEntityDAO<RoomService> {
    private HibernateService hibernateService;

    @InjectValue
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public List<RoomService> getAll() {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.createQuery("FROM RoomService", RoomService.class).list();
        }
        try (session) {
            return session.createQuery("FROM RoomService", RoomService.class).list();
        }
    }

    @Override
    public RoomService getById(long id) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.get(RoomService.class, id);
        }
        try (session) {
            return session.get(RoomService.class, id);
        }
    }

    @Override
    public void saveAll(List<RoomService> roomServices) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            for (RoomService roomService : roomServices) {
                session.persist(roomService);
            }
        } else {
            try (session) {
                Transaction transaction = session.beginTransaction();
                for (RoomService roomService : roomServices) {
                    session.persist(roomService);
                }
                transaction.commit();
            }
        }
    }

    @Override
    public RoomService update(RoomService roomService) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            return session.merge(roomService);
        }
        try (session) {
            return session.merge(roomService);
        }
    }

    @Override
    public void save(RoomService roomService) {
        Session session = hibernateService.getSession();
        if (session.getTransaction().isActive()) {
            session.persist(roomService);
        } else {
            try (session) {
                session.persist(roomService);
            }
        }
    }
}
