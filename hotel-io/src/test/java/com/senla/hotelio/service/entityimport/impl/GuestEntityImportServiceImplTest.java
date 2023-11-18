package com.senla.hotelio.service.entityimport.impl;

import com.senla.hoteldb.entity.Guest;
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
class GuestEntityImportServiceImplTest {

    @InjectMocks
    private GuestEntityImportServiceImpl guestEntityImportService;
    private Guest guest1;

    @BeforeEach
    void setUp() {
        guest1 = new Guest(1L, "Ivan", "Ivanov", "ivanov@mail.com", "pass", "role");
    }

    @Test
    void importEntities() throws IOException, HotelIoModuleException {
        String fileName = "Guest";
        String inputCsv = "1,Ivan,Ivanov,ivanov@mail.com,pass,role";
        Guest guestToBeChecked = guest1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"))) {
            writer.write(inputCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReflectionTestUtils.setField(guestEntityImportService, "ENTITY_NAME", fileName);
        ReflectionTestUtils.setField(guestEntityImportService, "csvImportPath", "");

        List<Guest> guestsResult = guestEntityImportService.importEntities();

        assertEquals(List.of(guestToBeChecked), guestsResult);
        assertTrue(Files.deleteIfExists(Path.of(fileName + ".csv")));
    }
}