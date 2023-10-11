package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.GuestServices;
import com.senla.hoteldb.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class GuestServicesDAOImpl implements IEntityDAO<GuestServices> {
    public static final String SELECT_FROM = "SELECT * FROM guest_service";
    public static final String SELECT_WHERE_ID = "SELECT * FROM guest_service WHERE id=?";
    public static final String INSERT_INTO = "INSERT INTO guest_service (guest_id, room_service_id, room_service_order_date) VALUES (?, ?, ?)";
    public static final String UPDATE = "UPDATE guest_service SET guest_id=?, room_service_id=?, room_service_order_date=? WHERE id=";
    private static final Logger logger = LoggerFactory.getLogger(GuestServicesDAOImpl.class);
    protected DatabaseService databaseService;
    private Connection connection;

    @InjectValue(key = "DatabaseService")
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public List<GuestServices> getAll() {
        connection = databaseService.getConnection();
        List<GuestServices> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = SELECT_FROM;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                GuestServices item = mapGuestServicesResultSet(resultSet);
                result.add(item);
            }
            resultSet.close();
            statement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("an error occurred during SQL operation->" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public GuestServices getById(long id) {
        connection = databaseService.getConnection();
        GuestServices result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WHERE_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = mapGuestServicesResultSet(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("an error occurred during SQL operation->" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void saveAll(List<GuestServices> guestServices) {
        Connection connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            for (GuestServices guestService : guestServices) {
                setGuestServicesValues(preparedStatement, guestService);
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("an error occurred during SQL operation->" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public GuestServices update(GuestServices guestServices) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE + guestServices.getId());
            setGuestServicesValues(preparedStatement, guestServices);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
            if (rowsAffected > 0) {
                return guestServices;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("an error occurred during SQL operation->" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(GuestServices guestServices) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            setGuestServicesValues(preparedStatement, guestServices);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("an error occurred during SQL operation->" + e.getMessage());
            e.printStackTrace();
        }
    }

    private GuestServices mapGuestServicesResultSet(ResultSet resultSet) throws SQLException {
        GuestServices guestService = new GuestServices();
        guestService.setId(resultSet.getLong("id"));
        guestService.setGuestId(resultSet.getLong("guest_id"));
        guestService.setRoomServiceId(resultSet.getLong("room_service_id"));
        guestService.setRoomServiceOrderDate(resultSet.getDate("room_service_order_date"));
        return guestService;
    }

    private void setGuestServicesValues(PreparedStatement preparedStatement, GuestServices guestServices) throws SQLException {
        preparedStatement.setLong(1, guestServices.getGuestId());
        preparedStatement.setLong(2, guestServices.getRoomServiceId());
        preparedStatement.setDate(3, new Date(guestServices.getRoomServiceOrderDate().getTime()));
    }
}
