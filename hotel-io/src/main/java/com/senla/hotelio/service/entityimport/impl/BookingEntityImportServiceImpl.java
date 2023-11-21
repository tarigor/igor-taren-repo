package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.util.EntityDtoMapper;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Room;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookingEntityImportServiceImpl extends ImportService implements IImportService<Booking> {
    public static final String PATTERN = "dd-MM-yyyy";
    private final String ENTITY_NAME = "Booking";
    private final GuestServiceImpl guestService;
    private final RoomServiceImpl roomService;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public BookingEntityImportServiceImpl(GuestServiceImpl guestService,
                                          RoomServiceImpl roomService,
                                          EntityDtoMapper entityDtoMapper) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @Override
    public ArrayList<Booking> importEntities() {
        ArrayList<Booking> bookings = new ArrayList<>();
        ArrayList<List<String>> bookingsWithParameters = getEntitiesFromCsv(ENTITY_NAME);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        for (List<String> bookingsWithParameter : bookingsWithParameters) {
            bookings.add(new Booking(
                    Long.parseLong(bookingsWithParameter.get(0)),
                    guestService.getById(Long.parseLong(bookingsWithParameter.get(1))),
                    entityDtoMapper.convertFromDtoToEntity(roomService.getRoom(Long.parseLong(bookingsWithParameter.get(2))), Room.class),
                    LocalDate.parse(bookingsWithParameter.get(3), dateTimeFormatter),
                    LocalDate.parse(bookingsWithParameter.get(4), dateTimeFormatter))
            );
        }
        return bookings;
    }
}
