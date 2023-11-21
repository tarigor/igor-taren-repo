package com.senla.hotel.service.impl;

import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.util.EntityDtoMapper;
import com.senla.hoteldb.entity.Room;
import com.senla.hoteldb.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.senla.hotel.enums.Ordering.ASC;
import static com.senla.hotel.enums.Ordering.DESC;
import static com.senla.hotel.enums.RoomSection.AVAILABILITY;
import static com.senla.hotel.enums.RoomSection.CAPACITY;
import static com.senla.hotel.enums.RoomSection.PRICE;
import static com.senla.hotel.enums.RoomSection.RATING;
import static com.senla.hotel.enums.RoomStatus.OCCUPIED;
import static com.senla.hotel.enums.RoomStatus.VACANT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private EntityDtoMapper entityDtoMapper;
    @InjectMocks
    private RoomServiceImpl roomService;
    private List<Room> rooms;
    private Room room1, room2, room3, room4;

    @BeforeEach
    void setUp() {
        room1 = new Room(1L, 2, 11.1, "VACANT", 3);
        room2 = new Room(2L, 1, 34.2, "OCCUPIED", 1);
        room3 = new Room(3L, 3, 44.1, "VACANT", 2);
        room4 = new Room(4L, 2, 23.2, "OCCUPIED", 3);
        rooms = Arrays.asList(room1, room2, room3, room4);
    }

    @Test
    void saveAllResultSizeTest() {
        when(roomRepository.saveAll(rooms)).thenReturn(rooms);

        List<Room> savedRooms = roomRepository.saveAll(rooms);

        assertEquals(rooms.size(), savedRooms.size());
    }

    @Test
    void saveAllMethodCallTest() {
        when(roomRepository.saveAll(rooms)).thenReturn(rooms);

        List<Room> savedRooms = roomRepository.saveAll(rooms);

        verify(roomRepository, times(1)).saveAll(rooms);
    }

    @Test
    void roomRegisterResultTest() {
        ReflectionTestUtils.setField(roomService, "checkInCheckOutPermission", true);
        String roomOperationString = "CHECKIN";

        Room room = room1;
        room.setRoomStatus(VACANT.name());

        when(roomRepository.findById(room1.getId())).thenReturn(Optional.of(room1));

        roomService.roomRegister(roomOperationString, room1.getId());

        assertEquals(OCCUPIED.name(), room.getRoomStatus());
    }

    @Test
    void changeRoomPriceResultTest() {
        double priceToBeChanged = 56.3;

        when(roomRepository.findById(room1.getId())).thenReturn(Optional.of(room1));

        roomService.changeRoomPrice(room1.getId(), priceToBeChanged);

        assertEquals(priceToBeChanged, room1.getPrice());
    }

    @Test
    void getRoomResultTest() {
        long idToBeChecked = room1.getId();

        when(roomRepository.findById(idToBeChecked)).thenReturn(Optional.of(room1));
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        RoomDto roomResult = roomService.getRoom(idToBeChecked);

        assertEquals(idToBeChecked, roomResult.getId());
    }

    @Test
    void addRoomResultTest() {
        RoomDto roomDto = new RoomDto(room1.getId(), room1.getCapacity(), room1.getPrice(), room1.getRoomStatus(), room1.getStarsRating());
        Room room = new Room(roomDto.getId(), roomDto.getCapacity(), roomDto.getPrice(), roomDto.getRoomStatus(), roomDto.getStarsRating());

        when(entityDtoMapper.convertFromDtoToEntity(roomDto, Room.class)).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(room);
        when(entityDtoMapper.convertFromEntityToDto(room, RoomDto.class)).thenReturn(roomDto);

        RoomDto result = roomService.addRoom(roomDto);

        assertEquals(roomDto, result);
    }

    @Test
    void getRoomsSortedByCapacityTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByCapacity = roomService.getSortedRooms(CAPACITY.name());

        int lastIndex = resultSortedByCapacity.size() - 1;
        assertTrue(resultSortedByCapacity.get(lastIndex).getCapacity() > resultSortedByCapacity.get(0).getCapacity());
    }

    @Test
    void getRoomsSortedByPriceTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByPrice = roomService.getSortedRooms(PRICE.name());

        int lastIndex = resultSortedByPrice.size() - 1;
        assertTrue(resultSortedByPrice.get(lastIndex).getPrice() > resultSortedByPrice.get(0).getPrice());
    }

    @Test
    void getRoomsSortedByAvailabilityTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByAvailability = roomService.getSortedRooms(AVAILABILITY.name());

        int lastIndex = resultSortedByAvailability.size() - 1;
        assertTrue(resultSortedByAvailability.get(lastIndex).getRoomStatus().compareTo(resultSortedByAvailability.get(0).getRoomStatus()) > 0);
    }

    @Test
    void getRoomsSortedByRatingTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByRating = roomService.getSortedRooms(RATING.name());

        int lastIndex = resultSortedByRating.size() - 1;
        assertTrue(resultSortedByRating.get(lastIndex).getStarsRating() > resultSortedByRating.get(0).getStarsRating());
    }

    @Test
    void getAvailableRoomsSortedByCapacityAscTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByCapacityAsc = roomService.getAvailableSortedRooms(CAPACITY.name(), ASC.name());

        int lastIndex = resultSortedByCapacityAsc.size() - 1;
        assertTrue(resultSortedByCapacityAsc.get(lastIndex).getRoomStatus().equals(VACANT.name())
                && resultSortedByCapacityAsc.get(0).getRoomStatus().equals(VACANT.name())
                && resultSortedByCapacityAsc.get(lastIndex).getCapacity() > resultSortedByCapacityAsc.get(0).getCapacity());
    }

    @Test
    void getAvailableRoomsSortedByPriceAscTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByPriceAsc = roomService.getAvailableSortedRooms(PRICE.name(), ASC.name());

        int lastIndex = resultSortedByPriceAsc.size() - 1;
        assertTrue(resultSortedByPriceAsc.get(lastIndex).getRoomStatus().equals(VACANT.name())
                && resultSortedByPriceAsc.get(0).getRoomStatus().equals(VACANT.name())
                && resultSortedByPriceAsc.get(lastIndex).getPrice() > resultSortedByPriceAsc.get(0).getPrice());
    }

    @Test
    void getAvailableRoomsSortedByAvailabilityAscTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByRatingAsc = roomService.getAvailableSortedRooms(RATING.name(), ASC.name());

        int lastIndex = resultSortedByRatingAsc.size() - 1;
        assertTrue(resultSortedByRatingAsc.get(lastIndex).getRoomStatus().equals(VACANT.name())
                && resultSortedByRatingAsc.get(0).getRoomStatus().equals(VACANT.name())
                && resultSortedByRatingAsc.get(lastIndex).getStarsRating() > resultSortedByRatingAsc.get(0).getStarsRating());
    }

    @Test
    void getAvailableRoomsSortedByCapacityDescTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByCapacityDesc = roomService.getAvailableSortedRooms(CAPACITY.name(), DESC.name());

        int lastIndex = resultSortedByCapacityDesc.size() - 1;
        assertTrue(resultSortedByCapacityDesc.get(lastIndex).getRoomStatus().equals(VACANT.name())
                && resultSortedByCapacityDesc.get(0).getRoomStatus().equals(VACANT.name())
                && resultSortedByCapacityDesc.get(lastIndex).getCapacity() < resultSortedByCapacityDesc.get(0).getCapacity());
    }

    @Test
    void getAvailableRoomsSortedByPriceDescTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByPriceDesc = roomService.getAvailableSortedRooms(PRICE.name(), DESC.name());

        int lastIndex = resultSortedByPriceDesc.size() - 1;
        assertTrue(resultSortedByPriceDesc.get(lastIndex).getRoomStatus().equals(VACANT.name())
                && resultSortedByPriceDesc.get(0).getRoomStatus().equals(VACANT.name())
                && resultSortedByPriceDesc.get(lastIndex).getPrice() < resultSortedByPriceDesc.get(0).getPrice());
    }

    @Test
    void getAvailableRoomsSortedByAvailabilityDescTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByAvailabilityDesc = roomService.getAvailableSortedRooms(AVAILABILITY.name(), DESC.name());

        int lastIndex = resultSortedByAvailabilityDesc.size() - 1;
        assertTrue(resultSortedByAvailabilityDesc.get(lastIndex).getRoomStatus().equals(VACANT.name())
                && resultSortedByAvailabilityDesc.get(0).getRoomStatus().equals(VACANT.name())
                && resultSortedByAvailabilityDesc.get(lastIndex).getRoomStatus().compareTo(resultSortedByAvailabilityDesc.get(0).getRoomStatus()) >= 0);
    }

    @Test
    void getAvailableRoomsSortedByRatingDescTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByRatingDesc = roomService.getAvailableSortedRooms(RATING.name(), DESC.name());

        int lastIndex = resultSortedByRatingDesc.size() - 1;
        assertTrue(resultSortedByRatingDesc.get(lastIndex).getRoomStatus().equals(VACANT.name())
                && resultSortedByRatingDesc.get(0).getRoomStatus().equals(VACANT.name())
                && resultSortedByRatingDesc.get(lastIndex).getStarsRating() < resultSortedByRatingDesc.get(0).getStarsRating());
    }

    @Test
    void getTotalAvailableRoomsResultTest() {
        int expectedAmount = 2;

        when(roomRepository.findAll()).thenReturn(rooms);

        int result = roomService.getTotalAvailableRooms();

        assertEquals(expectedAmount, result);
    }

    @Test
    void getRoomPriceResultTest() {
        long checkedRoomId = room1.getId();
        double checkedRoomPrice = rooms.stream().filter(r -> r.getId() == checkedRoomId).findAny().get().getPrice();

        when(roomRepository.findById(checkedRoomId)).thenReturn(rooms.stream().filter(r -> r.getId() == checkedRoomId).findAny());

        double result = roomService.getRoomPrice(checkedRoomId);

        assertEquals(checkedRoomPrice, result);
    }

    @Test
    void getAllOrderedByCapacityAscTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByCapacityAsc = roomService.getAllOrdered(CAPACITY.name(), ASC.name());

        int lastIndex = resultSortedByCapacityAsc.size() - 1;
        assertTrue(resultSortedByCapacityAsc.get(lastIndex).getCapacity() > resultSortedByCapacityAsc.get(0).getCapacity());
    }

    @Test
    void getAllOrderedByPriceAscTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByPriceAsc = roomService.getAllOrdered(PRICE.name(), ASC.name());

        int lastIndex = resultSortedByPriceAsc.size() - 1;
        assertTrue(resultSortedByPriceAsc.get(lastIndex).getPrice() > resultSortedByPriceAsc.get(0).getPrice());
    }

    @Test
    void getAllOrderedByAvailabilityAscTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByAvailabilityAsc = roomService.getAllOrdered(AVAILABILITY.name(), ASC.name());

        int lastIndex = resultSortedByAvailabilityAsc.size() - 1;
        assertTrue(resultSortedByAvailabilityAsc.get(lastIndex).getRoomStatus().compareTo(resultSortedByAvailabilityAsc.get(0).getRoomStatus()) >= 0);
    }

    @Test
    void getAllOrderedByRatingAscTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByRatingAsc = roomService.getAllOrdered(RATING.name(), ASC.name());

        int lastIndex = resultSortedByRatingAsc.size() - 1;
        assertTrue(resultSortedByRatingAsc.get(lastIndex).getStarsRating() > resultSortedByRatingAsc.get(0).getStarsRating());
    }

    @Test
    void getAllOrderedByCapacityDescTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByCapacityDesc = roomService.getAllOrdered(CAPACITY.name(), DESC.name());

        int lastIndex = resultSortedByCapacityDesc.size() - 1;
        assertTrue(resultSortedByCapacityDesc.get(lastIndex).getCapacity() < resultSortedByCapacityDesc.get(0).getCapacity());
    }

    @Test
    void getAllOrderedByPriceDescTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByPriceDesc = roomService.getAllOrdered(PRICE.name(), DESC.name());

        int lastIndex = resultSortedByPriceDesc.size() - 1;
        assertTrue(resultSortedByPriceDesc.get(lastIndex).getPrice() < resultSortedByPriceDesc.get(0).getPrice());
    }

    @Test
    void getAllOrderedByAvailabilityDescTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByAvailabilityDesc = roomService.getAllOrdered(AVAILABILITY.name(), DESC.name());

        int lastIndex = resultSortedByAvailabilityDesc.size() - 1;
        assertTrue(resultSortedByAvailabilityDesc.get(lastIndex).getRoomStatus().compareTo(resultSortedByAvailabilityDesc.get(0).getRoomStatus()) <= 0);
    }

    @Test
    void getAllOrderedByRatingDescTest() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> resultSortedByRatingDesc = roomService.getAllOrdered(RATING.name(), DESC.name());

        int lastIndex = resultSortedByRatingDesc.size() - 1;
        assertTrue(resultSortedByRatingDesc.get(lastIndex).getStarsRating() <= resultSortedByRatingDesc.get(0).getStarsRating());
    }

    @Test
    void updateAllAndSaveIfNotExistSaveExistingRoomMethodCallTest() {
        Room existingRoom = room1;
        Room newRoom = new Room(22L, 1, 21.2, "OCCUPIED", 2);

        when(roomRepository.findById(existingRoom.getId())).thenReturn(Optional.of(existingRoom));
        when(roomRepository.findById(newRoom.getId())).thenReturn(Optional.empty());

        roomService.updateAllAndSaveIfNotExist(new ArrayList<>(List.of(existingRoom, newRoom)));

        verify(roomRepository, times(1)).save(existingRoom);
    }

    @Test
    void updateAllAndSaveIfNotExistSaveNewRoomMethodCallTest() {
        Room existingRoom = room1;
        Room newRoom = new Room(22L, 1, 21.2, "OCCUPIED", 2);

        when(roomRepository.findById(existingRoom.getId())).thenReturn(Optional.of(existingRoom));
        when(roomRepository.findById(newRoom.getId())).thenReturn(Optional.empty());

        roomService.updateAllAndSaveIfNotExist(new ArrayList<>(List.of(existingRoom, newRoom)));

        verify(roomRepository, times(1)).save(newRoom);
    }

    @Test
    void getAllResultSizeTest() {
        when(roomRepository.findAll()).thenReturn(rooms);

        List<Room> actualRooms = roomService.getAll();

        assertEquals(rooms.size(), actualRooms.size());
        assertEquals(rooms, actualRooms);
    }

    @Test
    void getAllSizeTest() {
        when(roomRepository.findAll()).thenReturn(rooms);

        List<Room> actualRooms = roomService.getAll();

        assertEquals(rooms.size(), actualRooms.size());
        assertEquals(rooms, actualRooms);
    }
}