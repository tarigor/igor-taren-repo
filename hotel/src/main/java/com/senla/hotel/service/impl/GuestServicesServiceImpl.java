package com.senla.hotel.service.impl;

import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.dto.GuestServicesDto;
import com.senla.hotel.service.IGuestServicesService;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hoteldb.repository.GuestServicesRepository;
import com.senla.hoteldb.repository.RoomServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuestServicesServiceImpl implements IGuestServicesService {
    private static final Logger logger = LoggerFactory.getLogger(GuestServicesServiceImpl.class);
    @Autowired
    private GuestServicesRepository guestServicesRepository;
    @Autowired
    private RoomServiceRepository roomServiceRepository;

    @Override
    public void saveAll(List<GuestServices> guestServices) {
        guestServicesRepository.saveAll(guestServices);
    }

    //    View the list of guest services and their price (sort by price, by date);
    @Transactional
    @Override
    public List<GuestServicesDto> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering) {
        List<RoomService> roomServices = roomServiceRepository.findAll();
        List<GuestServices> guestServicesByGuestId = guestServicesRepository.findAll().stream()
                .filter(guestServices -> guestServices.getGuest().getId() == guestId).toList();

        Comparator<GuestServicesDto> comparator = null;

        switch (guestServicesSection) {
            case PRICE -> comparator = Comparator.comparing(GuestServicesDto::getRoomServicePrice);
            case DATE -> comparator = Comparator.comparing(GuestServicesDto::getRoomServiceOrderDate);
            default -> logger.error("An ordering by section -> {} is not possible", guestServicesSection);
        }

        if (ordering == Ordering.DESC) {
            comparator = comparator.reversed();
        }

        return guestServicesByGuestId.stream()
                .map(guestServices -> new GuestServicesDto(
                        guestServices.getId(),
                        guestServices.getGuest().getId(),
                        ServiceType.valueOf(roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomService().getId())).getServiceType()),
                        guestServices.getRoomServiceOrderDate(),
                        roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomService().getId())).getPrice()
                ))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private int getIndexByServiceID(List<RoomService> roomServices, long roomServiceId) {
        for (int i = 0; i < roomServices.size(); i++) {
            if (roomServices.get(i).getId() == roomServiceId) {
                return i;
            }
        }
        logger.error("there is no such record in RoomService");
        return 0;
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<GuestServices> guestServicesList) {
        for (GuestServices guestServices : guestServicesList) {
            Optional<GuestServices> guestServicesOptional = guestServicesRepository.findById(guestServices.getId());
            if (guestServicesOptional.isPresent()) {
                GuestServices guestServicesUpdate = guestServicesOptional.get();
                guestServicesUpdate.setGuest(guestServices.getGuest());
                guestServicesUpdate.setRoomService(guestServices.getRoomService());
                guestServicesUpdate.setRoomServiceOrderDate(guestServices.getRoomServiceOrderDate());
                guestServicesRepository.save(guestServicesUpdate);
            } else {
                guestServicesRepository.save(guestServices);
            }
        }
    }

    @Override
    public List<GuestServices> getAll() {
        return guestServicesRepository.findAll();
    }

    @Override
    public GuestServices getById(long id) {
        return guestServicesRepository.findById(id).orElseThrow(() -> new NoSuchElementException("there is no such a guest service with id->" + id));
    }
}
