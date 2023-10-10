package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.RoomService;
import com.senla.hoteldb.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomServiceDAOImpl implements IEntityDAO<RoomService> {
    public static final String SELECT_FROM = "SELECT * FROM room_service";
    public static final String SELECT_WHERE_ID = "SELECT * FROM room_service WHERE id=?";
    public static final String INSERT_INTO = "INSERT INTO room_service (service_type, price) VALUES (?, ?)";
    public static final String UPDATE = "UPDATE room_service SET service_type=?, price=? WHERE id=";
    private static final Logger logger = LoggerFactory.getLogger(RoomServiceDAOImpl.class);
    protected DatabaseService databaseService;
    private Connection connection;

    @InjectValue(key = "DatabaseService")
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public List<RoomService> getAll() {
        connection = databaseService.getConnection();
        List<RoomService> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = SELECT_FROM;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                RoomService item = mapRoomServiceResultSet(resultSet);
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
    public RoomService getById(long id) {
        connection = databaseService.getConnection();
        RoomService result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WHERE_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = mapRoomServiceResultSet(resultSet);
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
    public void saveAll(List<RoomService> roomServices) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            for (RoomService roomService : roomServices) {
                setRoomServiceValues(preparedStatement, roomService);
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
    public RoomService update(RoomService roomService) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE + roomService.getId());
            setRoomServiceValues(preparedStatement, roomService);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
            if (rowsAffected > 0) {
                return roomService;
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
    public void save(RoomService roomService) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            setRoomServiceValues(preparedStatement, roomService);
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

    private RoomService mapRoomServiceResultSet(ResultSet resultSet) throws SQLException {
        RoomService roomService = new RoomService();
        roomService.setId(resultSet.getLong("id"));
        roomService.setServiceType(ServiceType.valueOf(resultSet.getString("service_type")));
        roomService.setPrice(resultSet.getDouble("price"));
        return roomService;
    }

    private void setRoomServiceValues(PreparedStatement preparedStatement, RoomService roomService) throws SQLException {
        preparedStatement.setString(1, roomService.getServiceType().toString());
        preparedStatement.setDouble(2, roomService.getPrice());
    }
}
