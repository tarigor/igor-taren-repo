package com.senla.hotel.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.dao.impl.GuestServicesDAOImpl;
import com.senla.hotel.dao.impl.RoomServiceDAOImpl;
import com.senla.hotel.dto.GuestServicesDTO;
import com.senla.hotel.dto.GuestServicesEntityDTO;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotel.service.CommonService;
import com.senla.hotel.service.IGuestServicesService;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@CreateInstanceAndPutInContainer
public class GuestServicesServiceImpl extends CommonService implements IGuestServicesService {
    private static final Set<Long> idHolder = new HashSet<>();
    private GuestServicesDAOImpl guestServicesDAO;
    private RoomServiceDAOImpl roomServiceDAO;
    private ArrayList<GuestServicesEntityDTO> guestServicesEntityDTOList;

    public void setGuestServicesEntityDTOList(HashMap<Long, ArrayList<GuestServicesEntityDTO>> guestServicesEntityDTOList) {
        this.guestServicesEntityDTOList = guestServicesEntityDTOList.get(1L);
    }

    @InjectValue(key = "GuestServicesDAOImpl")
    public void setGuestServicesDAO(GuestServicesDAOImpl guestServicesDAO) {
        this.guestServicesDAO = guestServicesDAO;
    }

    @InjectValue(key = "RoomServiceDAOImpl")
    public void setRoomServiceDAO(RoomServiceDAOImpl roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    @Override
    public void saveAll(List<GuestServicesEntityDTO> guestServicesEntityDTOList) {
        ArrayList<GuestServices> guestServices = new ArrayList<>();
        for (int i = 0; i < guestServicesEntityDTOList.size(); i++) {
            guestServices.add(i, guestServiceConvertFromDTOtoEntity(guestServicesEntityDTOList.get(i)));
            guestServices.get(i).setServicesOrdered(guestServices.get(i).getServicesOrdered().replace(",", ";"));
        }
        for (GuestServices guestService : guestServices) {
            setId(guestService);
        }
        guestServicesDAO.saveAll(guestServices);
    }

    //    View the list of guest services and their price (sort by price, by date);
    @Override
    public List<GuestServicesDTO> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering) {
        saveAll(guestServicesEntityDTOList);
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

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<GuestServices> guestServicesList) {
        for (GuestServices guestServices : guestServicesList) {
            if (guestServicesDAO.getById(guestServices.getId()) != null) {
                guestServicesDAO.update(guestServices);
            } else {
                setId(guestServices);
                guestServicesDAO.save(guestServices);
            }
        }
    }

    @Override
    public List<GuestServicesEntityDTO> getAll() {
        saveAll(guestServicesEntityDTOList);
        List<GuestServices> guestServices = guestServicesDAO.getAll();
        ArrayList<GuestServicesEntityDTO> guestServicesEntityDTOList = new ArrayList<>();
        for (int i = 0; i < guestServices.size(); i++) {
            guestServicesEntityDTOList.add(i, guestServiceConvertFromEntityToDTO(guestServices.get(i)));
        }
        return guestServicesEntityDTOList;
    }

    @Override
    public GuestServicesEntityDTO getById(long id) {
        return guestServiceConvertFromEntityToDTO(guestServicesDAO.getById(id));
    }

    private GuestServices guestServiceConvertFromDTOtoEntity(GuestServicesEntityDTO guestServicesEntityDTO) {
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

    private void setId(GuestServices guestServices) {
        if (guestServices.getId() == 0) {
            guestServices.setId(generateId(idHolder));
        }
    }
}
