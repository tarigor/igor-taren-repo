package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Booking;
import com.senla.hoteldb.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class BookingDAOImpl implements IEntityDAO<Booking> {
    public static final String SELECT_FROM = "SELECT * FROM booking";
    public static final String SELECT_WHERE_ID = "SELECT * FROM booking WHERE id=?";
    public static final String INSERT_INTO = "INSERT INTO booking (guest_id, room_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE booking SET guest_id=?, room_id=?, check_in_date=?, check_out_date=? WHERE id=";
    protected DatabaseService databaseService;

    private Connection connection;

    @InjectValue(key = "DatabaseService")
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public List<Booking> getAll() {
        connection = databaseService.getConnection();
        List<Booking> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = SELECT_FROM;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Booking item = mapBookingResultSet(resultSet);
                result.add(item);
            }
            resultSet.close();
            statement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Booking getById(long id) {
        connection = databaseService.getConnection();
        Booking result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WHERE_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = mapBookingResultSet(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void saveAll(List<Booking> bookings) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            for (Booking booking : bookings) {
                setBookingValues(preparedStatement, booking);
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Booking update(Booking booking) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE + booking.getId());
            setBookingValues(preparedStatement, booking);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
            if (rowsAffected > 0) {
                return booking;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Booking booking) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            setBookingValues(preparedStatement, booking);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Booking mapBookingResultSet(ResultSet resultSet) throws SQLException {
        Booking booking = new Booking();
        booking.setId(resultSet.getLong("id"));
        booking.setGuestId(resultSet.getLong("guest_id"));
        booking.setBookedRoomId(resultSet.getLong("room_id"));
        booking.setCheckInDate(resultSet.getDate("check_in_date"));
        booking.setCheckOutDate(resultSet.getDate("check_out_date"));
        return booking;
    }

    private void setBookingValues(PreparedStatement preparedStatement, Booking booking) throws SQLException {
        preparedStatement.setLong(1, booking.getGuestId());
        preparedStatement.setLong(2, booking.getBookedRoomId());
        preparedStatement.setDate(3, new Date(booking.getCheckInDate().getTime()));
        preparedStatement.setDate(4, new Date(booking.getCheckOutDate().getTime()));
    }
}
