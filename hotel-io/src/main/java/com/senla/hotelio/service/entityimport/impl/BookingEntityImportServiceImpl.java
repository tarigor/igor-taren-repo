package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.util.EntityDtoMapper;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Room;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingEntityImportServiceImpl extends ImportService implements IImportService<Booking> {
    private static final Logger logger = LoggerFactory.getLogger(BookingEntityImportServiceImpl.class);
    private final String ENTITY_NAME = "Booking";

    private GuestServiceImpl guestService;
    private RoomServiceImpl roomService;
    @Autowired
    private EntityDtoMapper entityDtoMapper;

    @Autowired
    public void setGuestService(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @Autowired
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
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
                logger.error("an error occurred during a parse operation -> {}", e.getMessage());
            }
        }
        return bookings;
    }
}
