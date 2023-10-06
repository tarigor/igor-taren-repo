package com.senla.hotel.repo.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.RoomStatus;
import com.senla.hotel.entity.Room;
import com.senla.hotel.repo.IEntityRepository;
import com.senla.hoteldb.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomRepositoryImpl implements IEntityRepository<Room> {
    public static final String SELECT_FROM = "SELECT * FROM room";
    public static final String SELECT_WHERE_ID = "SELECT * FROM room WHERE id=?";
    public static final String INSERT_INTO = "INSERT INTO room (capacity, price, room_status, stars_rating) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE room SET capacity=?, price=?, room_status=?, stars_rating=? WHERE id=";
    protected DatabaseService databaseService;

    private static Room mapRoomResultSet(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong("id"));
        room.setCapacity(resultSet.getInt("capacity"));
        room.setPrice(resultSet.getDouble("price"));
        room.setRoomStatus(RoomStatus.valueOf(resultSet.getString("room_status")));
        room.setStarsRating(resultSet.getInt("stars_rating"));
        return room;
    }

    @InjectValue(key = "DatabaseService")
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public List<Room> getAll() {
        List<Room> result = new ArrayList<>();
        Connection connection = databaseService.getConnection();
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
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Room getById(long id) {
        Connection connection = databaseService.getConnection();
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
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void saveAll(List<Room> rooms) {
        Connection connection = databaseService.getConnection();
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
            e.printStackTrace();
        }
    }

    @Override
    public Room update(Room room) {
        Connection connection = databaseService.getConnection();
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
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Room room) {
        Connection connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            setRoomValues(preparedStatement, room);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setRoomValues(PreparedStatement preparedStatement, Room room) throws SQLException {
        preparedStatement.setInt(1, room.getCapacity());
        preparedStatement.setDouble(2, room.getPrice());
        preparedStatement.setString(3, room.getRoomStatus().toString());
        preparedStatement.setInt(4, room.getStarsRating());
    }
}
