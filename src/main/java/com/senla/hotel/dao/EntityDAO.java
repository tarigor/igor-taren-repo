package com.senla.hotel.dao;

import java.util.Set;

public abstract class EntityDAO {
    protected long generateId(Set<Long> idHolder) {
        long id = 0;
        if (idHolder.isEmpty()) {
            id = 1;
        } else {
            id = idHolder.size() + 1;
        }
        idHolder.add(id);
        return id;
    }
}
