package com.senla.hotel.dao.service;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.RoomStatus;
import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.constant.Table;
import com.senla.hotel.entity.*;
import com.senla.hoteldb.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class DAOService {

    public static final String UPDATE_BOOKING = "UPDATE booking SET guest_id=?, room_id=?, check_in_date=?, check_out_date=? WHERE id=";
    public static final String UPDATE_GUEST = "UPDATE guest SET first_name=?, last_name=? WHERE id=";
    public static final String UPDATE_GUEST_SERVICE = "UPDATE guest_service SET guest_id=?, room_service_id=?, room_service_order_date=? WHERE id=";
    public static final String UPDATE_ROOM = "UPDATE room SET capacity=?, price=?, room_status=?, stars_rating=? WHERE id=";
    public static final String UPDATE_ROOM_SERVICE = "UPDATE room_service SET service_type=?, price=? WHERE id=";
    public static final String INSERT_INTO = "INSERT INTO ";
    public static final String INSERT_BOOKING = " (guest_id, room_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
    public static final String INSERT_GUEST = " (first_name, last_name) VALUES (?, ?)";
    public static final String INSERT_GUEST_SERVICE = " (guest_id, room_service_id, room_service_order_date) VALUES (?, ?, ?)";
    public static final String INSERT_ROOM = " (capacity, price, room_status, stars_rating) VALUES (?, ?, ?, ?)";
    public static final String INSERT_ROOM_SERVICE = " (service_type, price) VALUES (?, ?)";
    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String WHERE_ID = " WHERE id = ?";
    private DatabaseService databaseService;

    private static <T> T mapResultSetToCustomClass(ResultSet resultSet, Table table) throws SQLException {
        switch (table) {
            case BOOKING:
                return (T) mapBookingResultSet(resultSet);
            case GUEST:
                return (T) mapGuestResultSet(resultSet);
            case GUEST_SERVICE:
                return (T) mapGuestServiceResultSet(resultSet);
            case ROOM:
                return (T) mapRoomResultSet(resultSet);
            case ROOM_SERVICE:
                return (T) mapRoomServiceResultSet(resultSet);
            default:
                throw new IllegalArgumentException("Unsupported table: " + table);
        }
    }

    private static Booking mapBookingResultSet(ResultSet resultSet) throws SQLException {
        Booking booking = new Booking();
        booking.setId(resultSet.getLong("id"));
        booking.setGuestId(resultSet.getLong("guest_id"));
        booking.setBookedRoomId(resultSet.getLong("room_id"));
        booking.setCheckInDate(resultSet.getDate("check_in_date"));
        booking.setCheckOutDate(resultSet.getDate("check_out_date"));
        return booking;
    }

    private static Guest mapGuestResultSet(ResultSet resultSet) throws SQLException {
        Guest guest = new Guest();
        guest.setId(resultSet.getLong("id"));
        guest.setFirstName(resultSet.getString("first_name"));
        guest.setLastName(resultSet.getString("last_name"));
        return guest;
    }

    private static GuestServices mapGuestServiceResultSet(ResultSet resultSet) throws SQLException {
        GuestServices guestService = new GuestServices();
        guestService.setId(resultSet.getLong("id"));
        guestService.setGuestId(resultSet.getLong("guest_id"));
        guestService.setRoomServiceId(resultSet.getLong("room_service_id"));
        guestService.setRoomServiceOrderDate(resultSet.getDate("room_service_order_date"));
        return guestService;
    }

    private static Room mapRoomResultSet(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong("id"));
        room.setCapacity(resultSet.getInt("capacity"));
        room.setPrice(resultSet.getDouble("price"));
        room.setRoomStatus(RoomStatus.valueOf(resultSet.getString("room_status")));
        room.setStarsRating(resultSet.getInt("stars_rating"));
        return room;
    }

    private static RoomService mapRoomServiceResultSet(ResultSet resultSet) throws SQLException {
        RoomService roomService = new RoomService();
        roomService.setId(resultSet.getLong("id"));
        roomService.setServiceType(ServiceType.valueOf(resultSet.getString("service_type")));
        roomService.setPrice(resultSet.getDouble("price"));
        return roomService;
    }

    @InjectValue(key = "DatabaseService")
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public <T> List<T> getAll(Table table) {
        List<T> result = new ArrayList<>();
        Connection connection = databaseService.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = SELECT_FROM + table.toString();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                T item = mapResultSetToCustomClass(resultSet, table);
                result.add(item);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
        return result;
    }

    public <T> T getById(long id, Table table) {
        T result = null;
        Connection connection = databaseService.getConnection();
        try {
            String query = SELECT_FROM + table.toString() + WHERE_ID;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = mapResultSetToCustomClass(resultSet, table);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> void saveAll(List<T> entities, Table table) {
        Connection connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(generateInsertQuery(table.name()));
            for (T entity : entities) {
                setEntityValues(preparedStatement, entity, table.name());
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> void save(T entity, Table table) {
        Connection connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(generateInsertQuery(table.name()));
            setEntityValues(preparedStatement, entity, table.name());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> T update(T entity, Table table) {
        Connection connection = databaseService.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(generateUpdateQuery(table.name(), entity));
            setEntityValues(preparedStatement, entity, table.name());
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            if (rowsAffected > 0) {
                return entity;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> void setEntityValues(PreparedStatement preparedStatement, T entity, String tableName) throws SQLException {
        switch (tableName.toLowerCase()) {
            case "booking":
                setBookingValues(preparedStatement, (Booking) entity);
                break;
            case "guest":
                setGuestValues(preparedStatement, (Guest) entity);
                break;
            case "guest_service":
                setGuestServiceValues(preparedStatement, (GuestServices) entity);
                break;
            case "room":
                setRoomValues(preparedStatement, (Room) entity);
                break;
            case "room_service":
                setRoomServiceValues(preparedStatement, (RoomService) entity);
                break;
            default:
                throw new IllegalArgumentException("Unknown table name: " + tableName);
        }
    }

    private void setBookingValues(PreparedStatement preparedStatement, Booking booking) throws SQLException {
        preparedStatement.setLong(1, booking.getGuestId());
        preparedStatement.setLong(2, booking.getBookedRoomId());
        preparedStatement.setDate(3, new Date(booking.getCheckInDate().getTime()));
        preparedStatement.setDate(4, new Date(booking.getCheckOutDate().getTime()));
    }

    private void setGuestValues(PreparedStatement preparedStatement, Guest guest) throws SQLException {
        preparedStatement.setString(1, guest.getFirstName());
        preparedStatement.setString(2, guest.getLastName());
    }

    private void setGuestServiceValues(PreparedStatement preparedStatement, GuestServices guestServices) throws SQLException {
        preparedStatement.setLong(1, guestServices.getGuestId());
        preparedStatement.setLong(2, guestServices.getRoomServiceId());
        preparedStatement.setDate(3, new Date(guestServices.getRoomServiceOrderDate().getTime()));
    }

    private void setRoomValues(PreparedStatement preparedStatement, Room room) throws SQLException {
        preparedStatement.setInt(1, room.getCapacity());
        preparedStatement.setDouble(2, room.getPrice());
        preparedStatement.setString(3, room.getRoomStatus().toString());
        preparedStatement.setInt(4, room.getStarsRating());
    }

    private void setRoomServiceValues(PreparedStatement preparedStatement, RoomService roomService) throws SQLException {
        preparedStatement.setString(1, roomService.getServiceType().toString());
        preparedStatement.setDouble(2, roomService.getPrice());
    }

    private String generateInsertQuery(String tableName) {
        switch (tableName.toLowerCase()) {
            case "booking":
                return INSERT_INTO + tableName + INSERT_BOOKING;
            case "guest":
                return INSERT_INTO + tableName + INSERT_GUEST;
            case "guest_service":
                return INSERT_INTO + tableName + INSERT_GUEST_SERVICE;
            case "room":
                return INSERT_INTO + tableName + INSERT_ROOM;
            case "room_service":
                return INSERT_INTO + tableName + INSERT_ROOM_SERVICE;
            default:
                throw new IllegalArgumentException("Unsupported table name: " + tableName);
        }
    }

    private String generateUpdateQuery(String tableName, Object entity) {
        switch (tableName.toLowerCase()) {
            case "booking":
                return UPDATE_BOOKING + ((Booking) entity).getId();
            case "guest":
                return UPDATE_GUEST + ((Guest) entity).getId();
            case "guest_service":
                return UPDATE_GUEST_SERVICE + ((GuestServices) entity).getId();
            case "room":
                return UPDATE_ROOM + ((Room) entity).getId();
            case "room_service":
                return UPDATE_ROOM_SERVICE + ((RoomService) entity).getId();
            default:
                throw new IllegalArgumentException("Unsupported table name: " + tableName);
        }
    }
}
