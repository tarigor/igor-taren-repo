package com.senla.hotelio.service.entityimport.impl;

import com.senla.hoteldb.entity.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GuestEntityImportServiceImplTest {
    public static final String CSV_IMPORT_PATH = "src/test/resources/csv/import/";
    @InjectMocks
    private GuestEntityImportServiceImpl guestEntityImportService;
    private Guest guest;

    @BeforeEach
    void setUp() {
        guest = new Guest(1L, "Ivan", "Ivanov", "ivanov@mail.com", "pass", "role");
    }

    @Test
    void importEntities() {
        Guest guestToBeChecked = guest;

        guestEntityImportService.setCsvImportPath(CSV_IMPORT_PATH);

        List<Guest> guestsResult = guestEntityImportService.importEntities();

        assertEquals(List.of(guestToBeChecked), guestsResult);
    }
}