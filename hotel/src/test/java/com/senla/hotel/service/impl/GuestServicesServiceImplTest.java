package com.senla.hotel.service.impl;

import com.senla.hotel.dto.GuestServicesDto;
import com.senla.hotel.enums.GuestServicesSection;
import com.senla.hotel.enums.Ordering;
import com.senla.hotel.enums.ServiceType;
import com.senla.hotel.util.EntityDtoMapper;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hoteldb.repository.GuestServicesRepository;
import com.senla.hoteldb.repository.RoomServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.senla.hotel.enums.ServiceType.CLEANING;
import static com.senla.hotel.enums.ServiceType.MAINTENANCE;
import static com.senla.hotel.enums.ServiceType.REPAIR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServicesServiceImplTest {
    public static final String PATTERN = "dd-MM-yyyy";
    @Mock
    private GuestServicesRepository guestServicesRepository;
    @Mock
    private RoomServiceRepository roomServiceRepository;
    @Mock
    private EntityDtoMapper entityDtoMapper;
    @InjectMocks
    private GuestServicesServiceImpl guestServicesService;
    private GuestServices guestServices1, guestServices2, guestServices3, guestServices4, guestServices5, guestServices6;
    private List<GuestServices> guestServicesList;
    private List<RoomService> roomServices;
    private Guest guest1, guest2, guest3;
    private RoomService roomService1, roomService2, roomService3;

    @BeforeEach
    void setUp() throws ParseException {
        guest1 = new Guest(1L, "Ivan", "Ivanov", "ivnov@mail.com", "", "");
        guest2 = new Guest(2L, "Petr", "Petrov", "petrov@mail.com", "", "");
        guest3 = new Guest(2L, "Sidr", "Sidorov", "sidorov@mail.com", "", "");

        roomService1 = new RoomService(1L, CLEANING.name(), 12.3);
        roomService2 = new RoomService(2L, REPAIR.name(), 16.2);
        roomService3 = new RoomService(3L, MAINTENANCE.name(), 14.6);
        roomServices = List.of(roomService1, roomService2, roomService3);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        guestServices1 = new GuestServices(1L, guest1, roomService1, LocalDate.parse("22-09-2023", dateTimeFormatter));
        guestServices2 = new GuestServices(2L, guest1, roomService2, LocalDate.parse("24-09-2023", dateTimeFormatter));
        guestServices3 = new GuestServices(3L, guest2, roomService2, LocalDate.parse("08-05-2023", dateTimeFormatter));
        guestServices4 = new GuestServices(4L, guest2, roomService3, LocalDate.parse("12-07-2023", dateTimeFormatter));
        guestServices5 = new GuestServices(5L, guest3, roomService1, LocalDate.parse("20-03-2023", dateTimeFormatter));
        guestServices6 = new GuestServices(6L, guest3, roomService3, LocalDate.parse("21-03-2023", dateTimeFormatter));
        guestServicesList = List.of(guestServices1, guestServices2, guestServices3, guestServices4, guestServices5, guestServices6);
    }

    @Test
    void saveAllResultSizeTest() {
        when(guestServicesRepository.saveAll(guestServicesList)).thenReturn(guestServicesList);

        List<GuestServices> savedGuestServices = guestServicesService.saveAll(guestServicesList);

        assertEquals(guestServicesList.size(), savedGuestServices.size());
    }

    @Test
    void saveAllMethodCallTest() {
        when(guestServicesRepository.saveAll(guestServicesList)).thenReturn(guestServicesList);

        List<GuestServices> savedGuestServices = guestServicesService.saveAll(guestServicesList);

        verify(guestServicesRepository, times(1)).saveAll(guestServicesList);
    }

    @Test
    void getByGuestIdSortedByPriceAscTest() {
        long guestId = guest1.getId();

        when(roomServiceRepository.findAll()).thenReturn(roomServices);
        when(guestServicesRepository.findAll()).thenReturn(guestServicesList);

        List<GuestServicesDto> resultGuestServicesDtoPriceAscSorted = guestServicesService.getByGuestIdSorted(guestId, GuestServicesSection.PRICE, Ordering.ASC);

        int lastIndex = resultGuestServicesDtoPriceAscSorted.size() - 1;
        assertTrue(resultGuestServicesDtoPriceAscSorted.get(lastIndex).getRoomServicePrice() > resultGuestServicesDtoPriceAscSorted.get(0).getRoomServicePrice());
    }

    @Test
    void getByGuestIdSortedByDateAscTest() {
        long guestId = guest1.getId();

        when(roomServiceRepository.findAll()).thenReturn(roomServices);
        when(guestServicesRepository.findAll()).thenReturn(guestServicesList);

        List<GuestServicesDto> resultGuestServicesDtoDateAscSorted = guestServicesService.getByGuestIdSorted(guestId, GuestServicesSection.DATE, Ordering.ASC);

        int lastIndex = resultGuestServicesDtoDateAscSorted.size() - 1;
        assertTrue(resultGuestServicesDtoDateAscSorted.get(lastIndex).getRoomServiceOrderDate().isAfter(resultGuestServicesDtoDateAscSorted.get(0).getRoomServiceOrderDate()));
    }

    @Test
    void getByGuestIdSortedByPriceDescTest() {
        long guestId = guest1.getId();

        when(roomServiceRepository.findAll()).thenReturn(roomServices);
        when(guestServicesRepository.findAll()).thenReturn(guestServicesList);

        List<GuestServicesDto> resultGuestServicesDtoPriceDescSorted = guestServicesService.getByGuestIdSorted(guestId, GuestServicesSection.PRICE, Ordering.DESC);

        int lastIndex = resultGuestServicesDtoPriceDescSorted.size() - 1;
        assertTrue(resultGuestServicesDtoPriceDescSorted.get(lastIndex).getRoomServicePrice() < resultGuestServicesDtoPriceDescSorted.get(0).getRoomServicePrice());
    }

    @Test
    void getByGuestIdSortedByDateDescTest() {
        long guestId = guest1.getId();

        when(roomServiceRepository.findAll()).thenReturn(roomServices);
        when(guestServicesRepository.findAll()).thenReturn(guestServicesList);

        List<GuestServicesDto> resultGuestServicesDtoDateDescSorted = guestServicesService.getByGuestIdSorted(guestId, GuestServicesSection.DATE, Ordering.DESC);

        int lastIndex = resultGuestServicesDtoDateDescSorted.size() - 1;
        assertTrue(resultGuestServicesDtoDateDescSorted.get(lastIndex).getRoomServiceOrderDate().isBefore(resultGuestServicesDtoDateDescSorted.get(0).getRoomServiceOrderDate()));
    }

    @Test
    void updateAllAndSaveIfNotExistFindByIdMethodCallTest() throws ParseException {
        GuestServices existingGuestServices = guestServices1;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        GuestServices newGuestServices = new GuestServices(22L, guest1, roomService1, LocalDate.parse("22-11-2023", dateTimeFormatter));

        when(guestServicesRepository.findById(existingGuestServices.getId())).thenReturn(Optional.of(existingGuestServices));
        when(guestServicesRepository.findById(newGuestServices.getId())).thenReturn(Optional.empty());
        when(guestServicesRepository.save(any(GuestServices.class))).thenReturn(null);

        guestServicesService.updateAllAndSaveIfNotExist(new ArrayList<>(List.of(existingGuestServices, newGuestServices)));

        verify(guestServicesRepository, times(2)).findById(anyLong());
    }

    @Test
    void updateAllAndSaveIfNotExistSaveMethodCallTest() {
        GuestServices existingGuestServices = guestServices1;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        GuestServices newGuestServices = new GuestServices(22L, guest1, roomService1, LocalDate.parse("22-11-2023", dateTimeFormatter));

        when(guestServicesRepository.findById(existingGuestServices.getId())).thenReturn(Optional.of(existingGuestServices));
        when(guestServicesRepository.findById(newGuestServices.getId())).thenReturn(Optional.empty());
        when(guestServicesRepository.save(any(GuestServices.class))).thenReturn(null);

        guestServicesService.updateAllAndSaveIfNotExist(new ArrayList<>(List.of(existingGuestServices, newGuestServices)));

        verify(guestServicesRepository, times(2)).save(any(GuestServices.class));
    }

    @Test
    void getAllResultSizeTest() {
        when(guestServicesRepository.findAll()).thenReturn(guestServicesList);

        List<GuestServices> resultGuestServicesList = guestServicesService.getAll();

        assertEquals(guestServicesList.size(), resultGuestServicesList.size());
    }

    @Test
    void getAllResultTest() {
        when(guestServicesRepository.findAll()).thenReturn(guestServicesList);

        List<GuestServices> resultGuestServicesList = guestServicesService.getAll();

        assertEquals(guestServicesList, resultGuestServicesList);
    }

    @Test
    void getByIdResultTest() {
        GuestServices guestServicesToBeChecked = guestServices1;
        long guestServicesIdToBeChecked = guestServices1.getId();
        GuestServicesDto guestServicesDtoToBeChecked = new GuestServicesDto(
                guestServicesToBeChecked.getId(),
                guestServicesToBeChecked.getGuest().getId(),
                ServiceType.valueOf(guestServicesToBeChecked.getRoomService().getServiceType()),
                guestServicesToBeChecked.getRoomServiceOrderDate(),
                guestServicesToBeChecked.getRoomService().getPrice()
        );

        when(guestServicesRepository.findById(guestServicesIdToBeChecked)).thenReturn(Optional.of(guestServicesToBeChecked));
        when(entityDtoMapper.convertFromEntityToDto(guestServicesToBeChecked, GuestServicesDto.class)).thenReturn(guestServicesDtoToBeChecked);

        GuestServicesDto result = guestServicesService.getById(guestServicesToBeChecked.getId());

        assertEquals(guestServicesDtoToBeChecked, result);
    }
}