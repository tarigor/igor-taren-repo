package com.senla.hotel.service;

import java.util.Set;

public abstract class CommonService {
    protected long generateId(Set<Long> idHolder) {
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
