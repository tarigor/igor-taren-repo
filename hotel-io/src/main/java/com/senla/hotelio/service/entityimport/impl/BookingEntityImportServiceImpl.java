package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.util.EntityDtoMapper;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Room;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookingEntityImportServiceImpl extends ImportService implements IImportService<Booking> {
    private final String ENTITY_NAME = "Booking";
    private final GuestServiceImpl guestService;
    private final RoomServiceImpl roomService;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public BookingEntityImportServiceImpl(GuestServiceImpl guestService, RoomServiceImpl roomService, EntityDtoMapper entityDtoMapper) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @Override
    public ArrayList<Booking> importEntities() throws HotelIoModuleException {
        ArrayList<Booking> bookings = new ArrayList<>();
        ArrayList<List<String>> bookingsWithParameters = getEntitiesFromCsv(ENTITY_NAME);
        for (List<String> bookingsWithParameter : bookingsWithParameters) {
            try {
                bookings.add(new Booking(
                        Long.parseLong(bookingsWithParameter.get(0)),
                        guestService.getById(Long.parseLong(bookingsWithParameter.get(1))),
                        entityDtoMapper.convertFromDtoToEntity(roomService.getRoom(Long.parseLong(bookingsWithParameter.get(2))), Room.class),
                        new SimpleDateFormat("yyyy-MM-dd").parse(bookingsWithParameter.get(3)),
                        new SimpleDateFormat("yyyy-MM-dd").parse(bookingsWithParameter.get(4))
                ));
            } catch (ParseException e) {
                log.error("an error occurred during a parse operation -> {}", e.getMessage());
            }
        }
        return bookings;
    }
}
