package com.senla.hotel.dao;

import java.util.List;

public interface IEntityDAO<T> {
    List<T> getAll();

    T getById(long entityId);

    void save(T entity);

    void saveAll(List<T> entities);

    T update(T entity);
}
