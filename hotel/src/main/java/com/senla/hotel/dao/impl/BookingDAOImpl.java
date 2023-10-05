package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.Table;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.dao.service.DAOService;
import com.senla.hotel.entity.Booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class BookingDAOImpl implements IEntityDAO<Booking> {

    private Map<Long, Booking> bookings = new HashMap<>();
    private DAOService daoService;

    @InjectValue(key = "DAOService")
    public void setDaoService(DAOService daoService) {
        this.daoService = daoService;
    }

    public Map<Long, Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Map<Long, Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public List<Booking> getAll() {
        return daoService.getAll(Table.BOOKING);
    }

    @Override
    public void saveAll(List<Booking> bookings) {
        daoService.saveAll(bookings, Table.BOOKING);
    }

    @Override
    public Booking getById(long bookingId) {
        return daoService.getById(bookingId, Table.BOOKING);
    }

    @Override
    public void save(Booking booking) {
        daoService.save(booking, Table.BOOKING);
    }

    @Override
    public Booking update(Booking booking) {
        return daoService.update(booking, Table.BOOKING);
    }
}
