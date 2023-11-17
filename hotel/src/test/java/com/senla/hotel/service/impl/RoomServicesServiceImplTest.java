package com.senla.hotel.service.impl;

import com.senla.hoteldb.entity.RoomService;
import com.senla.hoteldb.repository.RoomServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.senla.hotel.enums.Ordering.ASC;
import static com.senla.hotel.enums.Ordering.DESC;
import static com.senla.hotel.enums.RoomServiceSection.PRICE;
import static com.senla.hotel.enums.RoomServiceSection.ROOM_SERVICE;
import static com.senla.hotel.enums.ServiceType.CLEANING;
import static com.senla.hotel.enums.ServiceType.MAINTENANCE;
import static com.senla.hotel.enums.ServiceType.REPAIR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServicesServiceImplTest {
    @Mock
    private RoomServiceRepository roomServiceRepository;
    @InjectMocks
    private RoomServicesServiceImpl roomServicesService;
    private RoomService roomService1, roomService2, roomService3;
    private List<RoomService> roomServices;

    @BeforeEach
    void setUp() {
        roomService1 = new RoomService(1L, CLEANING.name(), 12.3);
        roomService2 = new RoomService(2L, REPAIR.name(), 16.2);
        roomService3 = new RoomService(3L, MAINTENANCE.name(), 14.6);
        roomServices = List.of(roomService1, roomService2, roomService3);
    }

    @Test
    void saveAll() {
        when(roomServiceRepository.saveAll(roomServices)).thenReturn(roomServices);

        List<RoomService> savedRoomServices = roomServicesService.saveAll(roomServices);

        assertEquals(roomServices.size(), savedRoomServices.size());
        verify(roomServiceRepository, times(1)).saveAll(roomServices);
    }

    @Test
    void getAllOrdered() {
        when(roomServiceRepository.findAll()).thenReturn(roomServices);

        List<RoomService> resultRoomServicesTypeAscOrdered = roomServicesService.getAllOrdered(ROOM_SERVICE, ASC);
        List<RoomService> resultRoomServicesPriceAscOrdered = roomServicesService.getAllOrdered(PRICE, ASC);
        List<RoomService> resultRoomServicesTypeDescOrdered = roomServicesService.getAllOrdered(ROOM_SERVICE, DESC);
        List<RoomService> resultRoomServicesPriceDescOrdered = roomServicesService.getAllOrdered(PRICE, DESC);

        int lastIndex = resultRoomServicesTypeAscOrdered.size() - 1;
        assertTrue(resultRoomServicesTypeAscOrdered.get(lastIndex).getServiceType().compareTo(resultRoomServicesTypeAscOrdered.get(0).getServiceType()) > 0);
        lastIndex = resultRoomServicesPriceAscOrdered.size() - 1;
        assertTrue(resultRoomServicesPriceAscOrdered.get(lastIndex).getPrice() > resultRoomServicesPriceAscOrdered.get(0).getPrice());
        lastIndex = resultRoomServicesTypeDescOrdered.size() - 1;
        assertTrue(resultRoomServicesTypeDescOrdered.get(lastIndex).getServiceType().compareTo(resultRoomServicesTypeDescOrdered.get(0).getServiceType()) < 0);
        lastIndex = resultRoomServicesPriceDescOrdered.size() - 1;
        assertTrue(resultRoomServicesPriceDescOrdered.get(lastIndex).getPrice() < resultRoomServicesPriceDescOrdered.get(0).getPrice());
    }

    @Test
    void updateAllAndSaveIfNotExist() {
        RoomService existingRoomService = roomService1;
        RoomService newRoomService = new RoomService(11L, CLEANING.name(), 44.3);

        when(roomServiceRepository.findById(existingRoomService.getId())).thenReturn(Optional.of(existingRoomService));
        when(roomServiceRepository.findById(newRoomService.getId())).thenReturn(Optional.empty());

        roomServicesService.updateAllAndSaveIfNotExist(new ArrayList<>(List.of(existingRoomService, newRoomService)));

        verify(roomServiceRepository, times(1)).save(existingRoomService);
        verify(roomServiceRepository, times(1)).save(newRoomService);
    }

    @Test
    void getAll() {
        when(roomServiceRepository.findAll()).thenReturn(roomServices);

        List<RoomService> resultRoomServices = roomServicesService.getAll();

        assertEquals(roomServices.size(), resultRoomServices.size());
        assertEquals(roomServices, resultRoomServices);
    }

    @Test
    void getById() {
        RoomService roomServiceToBeChecked = roomService1;
        long roomServiceIdToBeChecked = roomServiceToBeChecked.getId();

        when(roomServiceRepository.findById(roomServiceIdToBeChecked)).thenReturn(Optional.of(roomServiceToBeChecked));

        RoomService result = roomServicesService.getById(roomServiceIdToBeChecked);

        assertNotNull(result);
        assertEquals(roomServiceToBeChecked, result);
        verify(roomServiceRepository, times(1)).findById(roomServiceIdToBeChecked);
    }
}