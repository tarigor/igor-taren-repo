package com.senla.hotelio.service.entityimport.impl;

import com.senla.hoteldb.entity.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.senla.hotel.enums.ServiceType.CLEANING;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RoomServiceEntityImportServiceImplTest {
    public static final String CSV_IMPORT_PATH = "src/test/resources/csv/import/";
    @InjectMocks
    private RoomServiceEntityImportServiceImpl roomServiceEntityImportService;
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        roomService = new RoomService(1L, CLEANING.name(), 12.3);
    }

    @Test
    void importEntitiesTest() {
        RoomService roomServiceToBeChecked = roomService;

        roomServiceEntityImportService.setCsvImportPath(CSV_IMPORT_PATH);

        List<RoomService> result = roomServiceEntityImportService.importEntities();

        assertEquals(List.of(roomServiceToBeChecked), result);
    }
}