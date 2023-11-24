package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.RoomService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static com.senla.hotel.enums.ServiceType.CLEANING;
import static com.senla.hotel.enums.ServiceType.MAINTENANCE;
import static com.senla.hotel.enums.ServiceType.REPAIR;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceEntityExportServiceImplTest {
    private final String ENTITY_FILENAME = "RoomServices";
    private final String FILE_PATH = "src/test/resources/csv/export/";
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
        roomServices = Arrays.asList(roomService1, roomService2, roomService3);
    }

    @Test
    void exportEntityFileExistTest() {
        roomServiceEntityExportService.setCsvExportPath(FILE_PATH);

        when(roomServicesService.getAll()).thenReturn(roomServices);

        roomServiceEntityExportService.exportEntity();

        assertTrue(Files.exists(Path.of(FILE_PATH)));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(FILE_PATH + ENTITY_FILENAME + ".csv"));
    }
}