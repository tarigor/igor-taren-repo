package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Guest;
import com.senla.hoteldb.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestDAOImpl implements IEntityDAO<Guest> {
    public static final String SELECT_FROM = "SELECT * FROM guest";
    public static final String SELECT_WHERE_ID = "SELECT * FROM guest WHERE id=?";
    public static final String INSERT_INTO = "INSERT INTO guest  (first_name, last_name) VALUES (?, ?)";
    public static final String UPDATE = "UPDATE guest SET first_name=?, last_name=? WHERE id=";
    protected DatabaseService databaseService;
    private Connection connection;

    @InjectValue(key = "DatabaseService")
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public List<Guest> getAll() {
        connection = databaseService.getConnection();
        List<Guest> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = SELECT_FROM;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Guest item = mapGuestResultSet(resultSet);
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
    public Guest getById(long id) {
        connection = databaseService.getConnection();
        Guest result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WHERE_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = mapGuestResultSet(resultSet);
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
    public void saveAll(List<Guest> guests) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            for (Guest guest : guests) {
                setGuestValues(preparedStatement, guest);
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
    public Guest update(Guest guest) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE + guest.getId());
            setGuestValues(preparedStatement, guest);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
            if (rowsAffected > 0) {
                return guest;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Guest guest) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            setGuestValues(preparedStatement, guest);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Guest mapGuestResultSet(ResultSet resultSet) throws SQLException {
        Guest guest = new Guest();
        guest.setId(resultSet.getLong("id"));
        guest.setFirstName(resultSet.getString("first_name"));
        guest.setLastName(resultSet.getString("last_name"));
        return guest;
    }

    private void setGuestValues(PreparedStatement preparedStatement, Guest guest) throws SQLException {
        preparedStatement.setString(1, guest.getFirstName());
        preparedStatement.setString(2, guest.getLastName());
    }
}
