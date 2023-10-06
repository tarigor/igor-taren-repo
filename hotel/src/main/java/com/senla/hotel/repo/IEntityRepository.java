package com.senla.hotel.repo;

import java.util.List;

public interface IEntityRepository<T> {
    List<T> getAll();

    T getById(long entityId);

    void save(T entity);

    void saveAll(List<T> entities);

    T update(T entity);
}
