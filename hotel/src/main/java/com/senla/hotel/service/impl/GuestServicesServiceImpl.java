package com.senla.hotel.service.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.dao.impl.GuestServicesDAOImpl;
import com.senla.hotel.dao.impl.RoomServiceDAOImpl;
import com.senla.hotel.dto.GuestServicesDTO;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.IGuestServicesService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@CreateInstanceAndPutInContainer
public class GuestServicesServiceImpl implements IGuestServicesService {
    private GuestServicesDAOImpl guestServicesDAO;
    private RoomServiceDAOImpl roomServiceDAO;

    @InjectValue
    public void setGuestServicesDAO(GuestServicesDAOImpl guestServicesDAO) {
        this.guestServicesDAO = guestServicesDAO;
    }

    @InjectValue
    public void setRoomServiceDAO(RoomServiceDAOImpl roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    @Override
    public void saveAll(List<GuestServices> guestServices) {
        guestServicesDAO.saveAll(guestServices);
    }

    //    View the list of guest services and their price (sort by price, by date);
    @Override
    public List<GuestServicesDTO> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering) {
        List<RoomService> roomServices = roomServiceDAO.getAll();
        List<GuestServices> guestServicesByGuestId = guestServicesDAO.getAll().stream()
                .filter(guestServices -> guestServices.getGuest().getId() == guestId)
                .collect(Collectors.toList());

        Comparator<GuestServicesDTO> comparator;

        switch (guestServicesSection) {
            case PRICE:
                comparator = Comparator.comparing(GuestServicesDTO::getRoomServicePrice);
                break;
            case DATE:
                comparator = Comparator.comparing(GuestServicesDTO::getRoomServiceOrderDate);
                break;
            default:
                throw new IndexOutOfBoundsException("An ordering by section -> " + guestServicesSection + " is not possible");
        }

        if (ordering == Ordering.DESC) {
            comparator = comparator.reversed();
        }

        return guestServicesByGuestId.stream()
                .map(guestServices -> new GuestServicesDTO(
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
