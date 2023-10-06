package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.repo.impl.BookingRepositoryImpl;

import java.util.List;

@CreateInstanceAndPutInContainer
public class BookingDAOImpl implements IEntityDAO<Booking> {
    private BookingRepositoryImpl bookingRepository;

    @InjectValue(key = "BookingRepositoryImpl")
    public void setBookingRepository(BookingRepositoryImpl bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.getAll();
    }

    @Override
    public void saveAll(List<Booking> bookings) {
        bookingRepository.saveAll(bookings);
    }

    @Override
    public Booking getById(long bookingId) {
        return bookingRepository.getById(bookingId);
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepository.update(booking);
    }
}
