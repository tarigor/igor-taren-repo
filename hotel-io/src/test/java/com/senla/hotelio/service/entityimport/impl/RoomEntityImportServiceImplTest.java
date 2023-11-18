package com.senla.hotelio.service.entityimport.impl;

import com.senla.hoteldb.entity.Room;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class RoomEntityImportServiceImplTest {

    @InjectMocks
    private RoomEntityImportServiceImpl roomEntityImportService;

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(1L, 2, 11.1, "VACANT", 3);
    }

    @Test
    void importEntities() throws IOException, HotelIoModuleException {
        String fileName = "Room";
        String inputCsv = "1,2,11.1,VACANT,3";
        Room roomToBeChecked = room;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"))) {
            writer.write(inputCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReflectionTestUtils.setField(roomEntityImportService, "ENTITY_NAME", fileName);
        ReflectionTestUtils.setField(roomEntityImportService, "csvImportPath", "");

        List<Room> result = roomEntityImportService.importEntities();

        assertEquals(List.of(roomToBeChecked), result);
        assertTrue(Files.deleteIfExists(Path.of(fileName + ".csv")));
    }
}