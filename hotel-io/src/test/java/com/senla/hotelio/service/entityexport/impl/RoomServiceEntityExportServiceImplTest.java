package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.senla.hotel.enums.ServiceType.CLEANING;
import static com.senla.hotel.enums.ServiceType.MAINTENANCE;
import static com.senla.hotel.enums.ServiceType.REPAIR;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceEntityExportServiceImplTest {
    private final String ENTITY_FILENAME = "RoomServices";
    @Mock
    private RoomServicesServiceImpl roomServicesService;
    @InjectMocks
    private RoomServiceEntityExportServiceImpl roomServiceEntityExportService;

    private List<RoomService> roomServices;

    @BeforeEach
    void setUp() {
        RoomService roomService1 = new RoomService(1L, CLEANING.name(), 12.3);
        RoomService roomService2 = new RoomService(2L, REPAIR.name(), 16.2);
        RoomService roomService3 = new RoomService(3L, MAINTENANCE.name(), 14.6);
        roomServices = List.of(roomService1, roomService2, roomService3);
    }

    @Test
    void exportEntity() throws HotelIoModuleException, IOException {
        String filePath = "";
        ReflectionTestUtils.setField(roomServiceEntityExportService, "ENTITY_FILENAME", ENTITY_FILENAME);
        ReflectionTestUtils.setField(roomServiceEntityExportService, "csvExportPath", filePath);

        when(roomServicesService.getAll()).thenReturn(roomServices);

        roomServiceEntityExportService.exportEntity();

        assertTrue(Files.exists(Path.of(filePath)));
        assertTrue(Files.deleteIfExists(Path.of(filePath + ENTITY_FILENAME + ".csv")));
    }
}