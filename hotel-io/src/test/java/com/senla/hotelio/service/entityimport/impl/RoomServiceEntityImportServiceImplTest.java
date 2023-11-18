package com.senla.hotelio.service.entityimport.impl;

import com.senla.hoteldb.entity.RoomService;
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

import static com.senla.hotel.enums.ServiceType.CLEANING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class RoomServiceEntityImportServiceImplTest {

    @InjectMocks
    private RoomServiceEntityImportServiceImpl roomServiceEntityImportService;
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        roomService = new RoomService(1L, CLEANING.name(), 12.3);
    }

    @Test
    void importEntities() throws HotelIoModuleException, IOException {
        String fileName = "RoomServices";
        String inputCsv = "1,CLEANING,12.3";
        RoomService roomServiceToBeChecked = roomService;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"))) {
            writer.write(inputCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReflectionTestUtils.setField(roomServiceEntityImportService, "ENTITY_NAME", fileName);
        ReflectionTestUtils.setField(roomServiceEntityImportService, "csvImportPath", "");

        List<RoomService> result = roomServiceEntityImportService.importEntities();

        assertEquals(List.of(roomServiceToBeChecked), result);
        assertTrue(Files.deleteIfExists(Path.of(fileName + ".csv")));
    }
}