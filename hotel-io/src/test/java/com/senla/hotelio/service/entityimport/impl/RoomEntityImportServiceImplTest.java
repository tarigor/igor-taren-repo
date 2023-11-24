package com.senla.hotelio.service.entityimport.impl;

import com.senla.hoteldb.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RoomEntityImportServiceImplTest {
    public static final String CSV_IMPORT_PATH = "src/test/resources/csv/import/";
    @InjectMocks
    private RoomEntityImportServiceImpl roomEntityImportService;

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(1L, 2, 11.1, "VACANT", 3);
    }

    @Test
    void importEntitiesTest() {
        Room roomToBeChecked = room;

        roomEntityImportService.setCsvImportPath(CSV_IMPORT_PATH);

        List<Room> result = roomEntityImportService.importEntities();

        assertEquals(List.of(roomToBeChecked), result);
    }
}