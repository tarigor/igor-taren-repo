package com.senla.hotel.service.impl;

import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.dto.GuestServicesDto;
import com.senla.hotel.service.IGuestServicesService;
import com.senla.hoteldb.dao.impl.GuestServicesDao;
import com.senla.hoteldb.dao.impl.RoomServiceDao;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hoteldb.service.HibernateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestServicesServiceImpl implements IGuestServicesService {
    private static final Logger logger = LoggerFactory.getLogger(GuestServicesServiceImpl.class);
    private GuestServicesDao guestServicesDAO;
    private RoomServiceDao roomServiceDAO;
    private HibernateService hibernateService;

    @Autowired
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Autowired
    public void setGuestServicesDAO(GuestServicesDao guestServicesDAO) {
        this.guestServicesDAO = guestServicesDAO;
    }

    @Autowired
    public void setRoomServiceDAO(RoomServiceDao roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    @Override
    public void saveAll(List<GuestServices> guestServices) {
        guestServicesDAO.saveAll(guestServices);
    }

    //    View the list of guest services and their price (sort by price, by date);
    @Override
    public List<GuestServicesDto> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering) {
        hibernateService.beginTransaction();
        List<RoomService> roomServices = roomServiceDAO.getAll();
        List<GuestServices> guestServicesByGuestId = guestServicesDAO.getAll().stream()
                .filter(guestServices -> guestServices.getGuest().getId() == guestId)
                .collect(Collectors.toList());

        Comparator<GuestServicesDto> comparator = null;

        switch (guestServicesSection) {
            case PRICE:
                comparator = Comparator.comparing(GuestServicesDto::getRoomServicePrice);
                break;
            case DATE:
                comparator = Comparator.comparing(GuestServicesDto::getRoomServiceOrderDate);
                break;
            default:
                logger.error("An ordering by section -> {} is not possible", guestServicesSection);
        }

        if (ordering == Ordering.DESC) {
            comparator = comparator.reversed();
        }

        List<GuestServicesDto> guestServicesDtoList = guestServicesByGuestId.stream()
                .map(guestServices -> new GuestServicesDto(
                        guestServices.getId(),
                        guestServices.getGuest().getId(),
                        ServiceType.valueOf(roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomService().getId())).getServiceType()),
                        guestServices.getRoomServiceOrderDate(),
                        roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomService().getId())).getPrice()
                ))
                .sorted(comparator)
                .collect(Collectors.toList());
        hibernateService.commit();
        return guestServicesDtoList;
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
            if (guestServicesDAO.getById(guestServices.getId()) != null) {
                guestServicesDAO.update(guestServices);
            } else {
                guestServicesDAO.save(guestServices);
            }
        }
    }

    @Override
    public List<GuestServices> getAll() {
        return guestServicesDAO.getAll();
    }

    @Override
    public GuestServices getById(long id) {
        return guestServicesDAO.getById(id);
    }
}
