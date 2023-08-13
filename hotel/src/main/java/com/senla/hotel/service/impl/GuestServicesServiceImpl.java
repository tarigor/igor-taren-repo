package com.senla.hotel.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.dao.impl.GuestServicesDAOImpl;
import com.senla.hotel.dao.impl.RoomServiceDAOImpl;
import com.senla.hotel.dto.GuestServicesDTO;
import com.senla.hotel.dto.GuestServicesEntityDTO;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.IGuestServicesService;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class GuestServicesServiceImpl implements IGuestServicesService {
    private static final GuestServicesServiceImpl INSTANCE = new GuestServicesServiceImpl();
    private final IEntityDAO<GuestServices> guestServicesDAO = GuestServicesDAOImpl.getInstance();
    private final IEntityDAO<RoomService> roomServiceDAO = RoomServiceDAOImpl.getInstance();

    public static GuestServicesServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveAll(List<GuestServicesEntityDTO> guestServicesEntityDTOList) {
        ArrayList<GuestServices> guestServices = new ArrayList<>();
        for (int i = 0; i < guestServicesEntityDTOList.size(); i++) {
            guestServices.add(i, guestServiceConvertFromDTOtoEntity(guestServicesEntityDTOList.get(i)));
            guestServices.get(i).setServicesOrdered(guestServices.get(i).getServicesOrdered().replace(",", ";"));
        }
        guestServicesDAO.saveAll(guestServices);
    }

    //    View the list of guest services and their price (sort by price, by date);
    @Override
    public List<GuestServicesDTO> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering) {
        switch (guestServicesSection) {
            case PRICE:
                return ordering == Ordering.ASC ?
                        getById(guestId)
                                .getServicesOrdered().entrySet().stream()
                                .map(e -> new GuestServicesDTO(e.getKey(), roomServiceDAO.getById(e.getValue())))
                                .sorted(Comparator.comparingDouble((GuestServicesDTO g) -> g.getRoomService().getPrice()))
                                .collect(Collectors.toList()) :
                        getById(guestId)
                                .getServicesOrdered().entrySet().stream()
                                .map(e -> new GuestServicesDTO(e.getKey(), roomServiceDAO.getById(e.getValue())))
                                .sorted(Comparator.comparingDouble((GuestServicesDTO g) -> g.getRoomService().getPrice()).reversed())
                                .collect(Collectors.toList());
            case DATE:
                return ordering == Ordering.ASC ?
                        getById(guestId).getServicesOrdered().entrySet().stream()
                                .map(e -> new GuestServicesDTO(e.getKey(), roomServiceDAO.getById(e.getValue())))
                                .sorted(Comparator.comparing(GuestServicesDTO::getDate))
                                .collect(Collectors.toList()) :
                        getById(guestId).getServicesOrdered().entrySet().stream()
                                .map(e -> new GuestServicesDTO(e.getKey(), roomServiceDAO.getById(e.getValue())))
                                .sorted(Comparator.comparing(GuestServicesDTO::getDate).reversed())
                                .collect(Collectors.toList());
            default:
                throw new IndexOutOfBoundsException("An ordering by section ->" + guestServicesSection + "is not possible");
        }
    }

    public void updateAllAndSaveIfNotExist(ArrayList<GuestServices> guestServicesList) {
        for (GuestServices guestServices : guestServicesList) {
            if (guestServicesDAO.getById(guestServices.getId()) != null) {
                guestServicesDAO.update(guestServices);
            } else {
                guestServicesDAO.save(guestServices);
            }
        }
    }

    public List<GuestServicesEntityDTO> getAll() {
        List<GuestServices> guestServices = guestServicesDAO.getAll();
        ArrayList<GuestServicesEntityDTO> guestServicesEntityDTOList = new ArrayList<>();
        for (int i = 0; i < guestServices.size(); i++) {
            guestServicesEntityDTOList.add(i, guestServiceConvertFromEntityToDTO(guestServices.get(i)));
        }
        return guestServicesEntityDTOList;
    }

    public GuestServicesEntityDTO getById(long id) {
        return guestServiceConvertFromEntityToDTO(guestServicesDAO.getById(id));
    }

    public GuestServices guestServiceConvertFromDTOtoEntity(GuestServicesEntityDTO guestServicesEntityDTO) {
        return new GuestServices(guestServicesEntityDTO.getGuestId(), mapToJsonStringConvert(guestServicesEntityDTO.getServicesOrdered()));
    }

    private GuestServicesEntityDTO guestServiceConvertFromEntityToDTO(GuestServices guestServices) {
        return new GuestServicesEntityDTO(guestServices.getId(), guestServices.getGuestId(), jsonStringToMapConvert(guestServices.getServicesOrdered()));
    }

    private Map<Date, Long> jsonStringToMapConvert(String servicesOrdered) {
        Gson gson = new Gson().newBuilder().setDateFormat("EEE MMM d H:mm:ss zzz yyyy").create();
        Type type = new TypeToken<Map<Date, Long>>() {
        }.getType();
        return gson.fromJson(servicesOrdered, type);
    }

    private String mapToJsonStringConvert(Map<Date, Long> servicesOrdered) {
        Gson gson = new Gson();
        return gson.toJson(servicesOrdered);
    }

}
