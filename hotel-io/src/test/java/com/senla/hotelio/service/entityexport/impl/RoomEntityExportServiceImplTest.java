package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hoteldb.entity.Room;
import com.senla.hotelio.service.entityexport.ExportService;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomEntityExportServiceImplTest {
    private final String ENTITY_FILENAME = "Room";
    private final String FILE_PATH = "src/test/resources/csv/export/";
    @Mock
    private RoomServiceImpl roomService;
    @InjectMocks
    private RoomEntityExportServiceImpl roomEntityExportService;
    private List<Room> rooms;

    @BeforeEach
    void setUp() {
        Room room1 = new Room(1L, 2, 11.1, "VACANT", 3);
        Room room2 = new Room(2L, 1, 34.2, "OCCUPIED", 1);
        Room room3 = new Room(3L, 3, 44.1, "VACANT", 2);
        Room room4 = new Room(4L, 2, 23.2, "OCCUPIED", 3);
        rooms = Arrays.asList(room1, room2, room3, room4);
    }

    @Test
    void exportEntityFileExistTest() {
        roomEntityExportService.setCsvExportPath(FILE_PATH);

        when(roomService.getAll()).thenReturn(rooms);

        roomEntityExportService.exportEntity();

        assertTrue(Files.exists(Path.of(FILE_PATH)));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(FILE_PATH + ENTITY_FILENAME + ".csv"));
    }
}