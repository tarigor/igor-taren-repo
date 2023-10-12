package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.RoomStatus;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Room;
import com.senla.hoteldb.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomDAOImpl implements IEntityDAO<Room> {
    public static final String SELECT_FROM = "SELECT * FROM room";
    public static final String SELECT_WHERE_ID = "SELECT * FROM room WHERE id=?";
    public static final String INSERT_INTO = "INSERT INTO room (capacity, price, room_status, stars_rating) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE room SET capacity=?, price=?, room_status=?, stars_rating=? WHERE id=";
    private static final Logger logger = LoggerFactory.getLogger(RoomDAOImpl.class);
    protected DatabaseService databaseService;
    private Connection connection;

    @InjectValue
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }


    @Override
    public List<Room> getAll() {
        List<Room> result = new ArrayList<>();
        connection = databaseService.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = SELECT_FROM;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Room item = mapRoomResultSet(resultSet);
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
    public Room getById(long id) {
        connection = databaseService.getConnection();
        Room result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WHERE_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = mapRoomResultSet(resultSet);
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
    public void saveAll(List<Room> rooms) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            for (Room room : rooms) {
                setRoomValues(preparedStatement, room);
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
    public Room update(Room room) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE + room.getId());
            setRoomValues(preparedStatement, room);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
            if (rowsAffected > 0) {
                return room;
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
    public void save(Room room) {
        connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            setRoomValues(preparedStatement, room);
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

    private Room mapRoomResultSet(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong("id"));
        room.setCapacity(resultSet.getInt("capacity"));
        room.setPrice(resultSet.getDouble("price"));
        room.setRoomStatus(RoomStatus.valueOf(resultSet.getString("room_status")));
        room.setStarsRating(resultSet.getInt("stars_rating"));
        return room;
    }

    private void setRoomValues(PreparedStatement preparedStatement, Room room) throws SQLException {
        preparedStatement.setInt(1, room.getCapacity());
        preparedStatement.setDouble(2, room.getPrice());
        preparedStatement.setString(3, room.getRoomStatus().toString());
        preparedStatement.setInt(4, room.getStarsRating());
    }
}
