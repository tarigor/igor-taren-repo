package com.senla.hotelweb.controller;

import com.senla.hotel.dto.BookingDto;
import com.senla.hotel.dto.GuestBookingDto;
import com.senla.hotel.enums.RoomOperation;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.validator.annotation.EnumValidator;
import com.senla.hotelio.service.entityexport.impl.CsvExportServiceImpl;
import com.senla.hotelio.service.entityimport.impl.CsvImportServiceImpl;
import com.senla.hotelio.service.enums.EntityName;
import com.senla.serialization.service.DeserializationService;
import com.senla.serialization.service.SerializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final BookingServiceImpl bookingService;

    private final RoomServiceImpl roomService;

    private final CsvImportServiceImpl csvImportService;

    private final CsvExportServiceImpl csvExportService;

    private final DeserializationService deserializationService;

    private final SerializationService serializationService;

    @Autowired
    public AdminController(BookingServiceImpl bookingService,
                           RoomServiceImpl roomService,
                           CsvImportServiceImpl csvImportService,
                           CsvExportServiceImpl csvExportService,
                           DeserializationService deserializationService,
                           SerializationService serializationService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
        this.csvImportService = csvImportService;
        this.csvExportService = csvExportService;
        this.deserializationService = deserializationService;
        this.serializationService = serializationService;
    }

    //4=List of guests and their rooms sorted alphabetically
    @GetMapping("/guests/rooms/alphabet")
    public List<GuestBookingDto> findAllOrderedAlphabetically() {
        return bookingService.findAllOrderedAlphabetically();
    }

    //5=List of guests and their rooms sorted by check-out date
    @GetMapping("/guests/rooms/checkout")
    public List<BookingDto> findAllOrderedByCheckOutDate() {
        return bookingService.findAllOrderedByCheckOutDate();
    }

    //6=Total number of available rooms
    @GetMapping("/rooms/available")
    public Integer getTotalAvailableRooms() {
        return roomService.getTotalAvailableRooms();
    }

    //7=Total number of guests
    @GetMapping("/guests/total")
    public long findCountOfAllGuests() {
        return bookingService.findCountOfAllGuests();
    }

    //10=View the last 3 guests of the room and the dates of their stay
    @GetMapping("/last/guestAmount/{amount}/room/{id}")
    public List<BookingDto> findLastGuestOfRoomAndDates(@PathVariable int amount, @PathVariable long id) {
        return bookingService.findLastGuestOfRoomAndDates(amount, id);
    }

    //15=Import the certain entity from the CSV file
    @GetMapping("/csv/importing/{entity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void importFromCsv(@PathVariable @EnumValidator(targetClassType = EntityName.class) String entity) {
        csvImportService.importEntity(entity);
    }

    //16=Export the certain entity
    @GetMapping("/csv/exporting/{entity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exportFromCsv(@PathVariable @EnumValidator(targetClassType = EntityName.class) String entity) {
        csvExportService.exportEntity(entity);
    }

    //17=Do serialization of entity
    @GetMapping("/serialization/{entity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void serialize(@PathVariable @EnumValidator(targetClassType = EntityName.class) String entity) {
        serializationService.serialize(entity);
    }

    //18=Do de-serialization of entity
    @GetMapping("/deserialization/{entity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deserialize(@PathVariable @EnumValidator(targetClassType = EntityName.class) String entity) {
        deserializationService.deserialize(entity);
    }

    //19=Do check-in/check-out from the room
    @GetMapping("/rooms/operation/{operation}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void roomRegister(@PathVariable @EnumValidator(targetClassType = RoomOperation.class) String operation, @PathVariable Long id) {
        roomService.roomRegister(operation, id);
    }
}
