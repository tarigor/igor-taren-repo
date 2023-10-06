package com.senla.hotel.service.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.dao.impl.GuestServicesDAOImpl;
import com.senla.hotel.dao.impl.RoomServiceDAOImpl;
import com.senla.hotel.dto.GuestServicesDTO;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.IGuestServicesService;

import java.util.*;
import java.util.stream.Collectors;

@CreateInstanceAndPutInContainer
public class GuestServicesServiceImpl implements IGuestServicesService {
    private GuestServicesDAOImpl guestServicesDAO;
    private RoomServiceDAOImpl roomServiceDAO;

    @InjectValue(key = "GuestServicesDAOImpl")
    public void setGuestServicesDAO(GuestServicesDAOImpl guestServicesDAO) {
        this.guestServicesDAO = guestServicesDAO;
    }

    @InjectValue(key = "RoomServiceDAOImpl")
    public void setRoomServiceDAO(RoomServiceDAOImpl roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    @Override
    public void saveAll(List< GuestServices> guestServices) {
        guestServicesDAO.saveAll(guestServices);
    }

    //    View the list of guest services and their price (sort by price, by date);
    @Override
    public List<GuestServicesDTO> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering) {
        List<RoomService> roomServices = roomServiceDAO.getAll();
        List<GuestServices> guestServicesBYGuestId = guestServicesDAO.getAll().stream()
                .filter(guestServices -> guestServices.getGuestId() == guestId).collect(Collectors.toList());
        switch (guestServicesSection) {
            case PRICE:
                return ordering == Ordering.ASC ?
                        guestServicesBYGuestId.stream()
                                .map(guestServices -> new GuestServicesDTO(
                                        guestServices.getId(),
                                        guestServices.getGuestId(),
                                        roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomServiceId())).getServiceType(),
                                        guestServices.getRoomServiceOrderDate(),
                                        roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomServiceId())).getPrice()
                                ))
                                .sorted(Comparator.comparing(GuestServicesDTO::getRoomServicePrice))
                                .collect(Collectors.toList()) :
                        guestServicesBYGuestId.stream()
                                .map(guestServices -> new GuestServicesDTO(
                                        guestServices.getId(),
                                        guestServices.getGuestId(),
                                        roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomServiceId())).getServiceType(),
                                        guestServices.getRoomServiceOrderDate(),
                                        roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomServiceId())).getPrice()
                                ))
                                .sorted(Comparator.comparing(GuestServicesDTO::getRoomServicePrice).reversed())
                                .collect(Collectors.toList());
            case DATE:
                return ordering == Ordering.ASC ?
                        guestServicesBYGuestId.stream()
                                .map(guestServices -> new GuestServicesDTO(
                                        guestServices.getId(),
                                        guestServices.getGuestId(),
                                        roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomServiceId())).getServiceType(),
                                        guestServices.getRoomServiceOrderDate(),
                                        roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomServiceId())).getPrice()
                                ))
                                .sorted(Comparator.comparing(GuestServicesDTO::getRoomServiceOrderDate))
                                .collect(Collectors.toList()) :
                        guestServicesBYGuestId.stream()
                                .map(guestServices -> new GuestServicesDTO(
                                        guestServices.getId(),
                                        guestServices.getGuestId(),
                                        roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomServiceId())).getServiceType(),
                                        guestServices.getRoomServiceOrderDate(),
                                        roomServices.get(getIndexByServiceID(roomServices, guestServices.getRoomServiceId())).getPrice()
                                ))
                                .sorted(Comparator.comparing(GuestServicesDTO::getRoomServiceOrderDate).reversed())
                                .collect(Collectors.toList());
            default:
                throw new IndexOutOfBoundsException("An ordering by section ->" + guestServicesSection + "is not possible");
        }
    }

    private int getIndexByServiceID(List<RoomService> roomServices, long roomServiceId) {
        for (int i = 0; i < roomServices.size(); i++) {
            if (roomServices.get(i).getId() == roomServiceId) {
                return i;
            }
        }
        throw new NoSuchElementException("there is no such record in RoomService");
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
