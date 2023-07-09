package com.senla.hotel.dao;

import java.util.List;
import java.util.Set;

public interface IEntityDAO<T> {
    List<T> getAll();

    T getById(long entityId);

    void save(T entity);

    void saveAll(List<T> entities);

    T update(T entity);

    default long generateId(Set<Long> idHolder) {
        long id;
        if (idHolder.isEmpty()) {
            id = 1;
        } else {
            id = idHolder.size() + 1;
        }
        idHolder.add(id);
        return id;
    }
}
