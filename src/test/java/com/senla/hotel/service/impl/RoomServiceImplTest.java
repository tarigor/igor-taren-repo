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
        roomService.doCheckIn(1);
        System.out.println("after check-in");
        rooms.values().forEach(System.out::println);

        roomService.doCheckOut(1);

        Assert.assertTrue(roomService.getRoom(1).isRoomAvailability());

        System.out.println("after check-out");
        rooms.values().forEach(System.out::println);

    }

    @Test
    public void testChangeRoomServiceStatus() {
        rooms.values().forEach(System.out::println);

        roomService.changeRoomServiceStatus(1, ServiceStatus.REPAIR);

        Assert.assertEquals(ServiceStatus.REPAIR, roomService.getRoom(1).getServiceStatus());

        System.out.println("after room service change");
        rooms.values().forEach(System.out::println);
    }

    @Test
    public void testChangeRoomPrice() {
        rooms.values().forEach(System.out::println);

        double newPrice = 11.1;

        roomService.changeRoomPrice(1, newPrice);

        Assert.assertTrue(newPrice == roomService.getRoom(1).getPrice());

        System.out.println("after price change");
        rooms.values().forEach(System.out::println);
    }

    @Test
    public void testGetRoom() {
        int roomId = 1;
        Assert.assertTrue(roomId == roomService.getRoom(roomId).getRoomId());
    }

    @Test
    public void testAddRoom() {
        roomService.addRoom(new Room(6,ServiceStatus.NONE,25.5,true));
        Assert.assertEquals(6,roomService.getRoom(6).getRoomId());
    }
}