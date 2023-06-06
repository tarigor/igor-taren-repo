package com.senla.hotel.service.impl;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.dao.impl.RoomDAOImpl;
import com.senla.hotel.entity.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class RoomServiceImplTest {

    private HashMap<Integer, Room> rooms;
    private RoomServiceImpl roomService;

    @Before
    public void init() {
        rooms = new HashMap<>();
        rooms.put(1, new Room(1, ServiceStatus.NONE, 23.3, true));
        rooms.put(2, new Room(2, ServiceStatus.NONE, 42.1, true));
        rooms.put(3, new Room(3, ServiceStatus.NONE, 25.7, true));

        RoomDAOImpl roomDAO = new RoomDAOImpl();
        roomDAO.setRooms(rooms);

        roomService = new RoomServiceImpl();
        roomService.setRoomDAO(roomDAO);
    }

    @Test
    public void testDoCheckIn() {
        rooms.values().forEach(System.out::println);

        roomService.doCheckIn(1);

        Assert.assertFalse(roomService.getRoom(1).isRoomAvailability());

        System.out.println("after check-in");
        rooms.values().forEach(System.out::println);
    }

    @Test
    public void testDoCheckOut() {
    }

    @Test
    public void testChangeRoomServiceStatus() {
    }

    @Test
    public void testChangeRoomPrice() {
    }

    @Test
    public void testGetRoomInfo() {
    }

    @Test
    public void testAddRoom() {
    }
}