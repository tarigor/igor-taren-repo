package com.senla.betterthenspring;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senla.container.ConfigProperty;
import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.FieldProperty;
import com.senla.container.TakeDataFromPropertiesFile;
import com.senla.hotel.constant.RoomStatus;
import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.dto.GuestServicesEntityDTO;
import com.senla.hotel.entity.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CreateInstanceAndPutInContainer
public class DataPuller {

    private static void findMapToBeFilled(Set<Class<?>> classesToInspect, HashMap<String, Object> instances) {
        for (Class<?> clazz : classesToInspect) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(TakeDataFromPropertiesFile.class)) {
                    Annotation annotation = method.getAnnotation(TakeDataFromPropertiesFile.class);
                    String entityName = ((TakeDataFromPropertiesFile) annotation).entityName();
                    List<Object[]> listOfObjectsFieldsList = takeListOfObjectsFieldsList(classesToInspect, entityName);
                    Type[] parameter = method.getGenericParameterTypes();
                    Object o = instances.get(clazz.getSimpleName());
                    try {
                        method.invoke(o, entityHandler(listOfObjectsFieldsList, parameter[0]));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private static <T> HashMap<Long, T> entityHandler(List<Object[]> listOfObjectsFieldsList, Type type) {
        switch (type.getTypeName()) {
            case ("java.util.Map<java.lang.Long, com.senla.hotel.entity.Room>"): {
                ArrayList<Room> rooms = new ArrayList<>();
                long id = 1;
                for (Object[] roomsWithParameter : listOfObjectsFieldsList) {
                    rooms.add(new Room(
                            id,
                            Integer.parseInt((String) roomsWithParameter[0]),
                            Double.parseDouble((String) roomsWithParameter[1]),
                            RoomStatus.valueOf((String) roomsWithParameter[2]),
                            Long.parseLong((String) roomsWithParameter[3]),
                            Integer.parseInt((String) roomsWithParameter[4])
                    ));
                    id = id + 1;
                }
                return (HashMap<Long, T>) createEntityMap(rooms);
            }

            case ("java.util.Map<java.lang.Long, com.senla.hotel.entity.Booking>"): {
                ArrayList<Booking> bookings = new ArrayList<>();
                long id = 1;
                for (Object[] bookingsWithParameter : listOfObjectsFieldsList) {
                    try {
                        bookings.add(new Booking(
                                id,
                                Long.parseLong((String) bookingsWithParameter[0]),
                                Long.parseLong((String) bookingsWithParameter[1]),
                                Long.parseLong((String) bookingsWithParameter[2]),
                                new SimpleDateFormat("dd/MM/yyyy").parse((String) bookingsWithParameter[3]),
                                new SimpleDateFormat("dd/MM/yyyy").parse((String) bookingsWithParameter[4])
                        ));
                        id = id + 1;
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                return (HashMap<Long, T>) createEntityMap(bookings);
            }

            case ("java.util.Map<java.lang.Long, com.senla.hotel.entity.Guest>"): {
                ArrayList<Guest> guests = new ArrayList<>();
                long id = 1;
                for (Object[] guestsWithParameter : listOfObjectsFieldsList) {
                    guests.add(new Guest(
                            id,
                            (String) guestsWithParameter[0],
                            (String) guestsWithParameter[1]
                    ));
                    id = id + 1;
                }
                return (HashMap<Long, T>) createEntityMap(guests);
            }

            case ("java.util.Map<java.lang.Long, com.senla.hotel.entity.GuestServices>"): {
                ArrayList<GuestServices> guestsServices = new ArrayList<>();
                long id = 1;
                for (Object[] guestsServicesWithParameter : listOfObjectsFieldsList) {
                    guestsServices.add(new GuestServices(
                            id,
                            Long.parseLong((String) guestsServicesWithParameter[0]),
                            (String) guestsServicesWithParameter[1]
                    ));
                    id = id + 1;
                }
                return (HashMap<Long, T>) createEntityMap(guestsServices);
            }
            case ("java.util.Map<java.lang.Long, com.senla.hotel.entity.RoomService>"): {
                ArrayList<RoomService> roomServices = new ArrayList<>();
                long id = 1;
                for (Object[] roomServiceWithParameter : listOfObjectsFieldsList) {
                    roomServices.add(new RoomService(
                            id,
                            ServiceType.valueOf((String) roomServiceWithParameter[0]),
                            Double.parseDouble((String) roomServiceWithParameter[1])
                    ));
                    id = id + 1;
                }
                return (HashMap<Long, T>) createEntityMap(roomServices);
            }
            case ("java.util.HashMap<java.lang.Long, java.util.ArrayList<com.senla.hotel.dto.GuestServicesEntityDTO>>"): {
                ArrayList<GuestServicesEntityDTO> guestServicesEntityDTOList = new ArrayList<>();
                HashMap<Long, ArrayList<GuestServicesEntityDTO>> guestServicesEntityDTOHashMap = new HashMap<>();
                long id = 1;
                for (Object[] guestServicesEntityDTO : listOfObjectsFieldsList) {
                    guestServicesEntityDTOList.add(new GuestServicesEntityDTO(
                            id,
                            jsonStringToMapConvert((String) guestServicesEntityDTO[1])
                    ));
                    id = id + 1;
                }
                guestServicesEntityDTOHashMap.put(1L, guestServicesEntityDTOList);
                return (HashMap<Long, T>) guestServicesEntityDTOHashMap;
            }
            default:
                throw new NoSuchElementException("There is no such HashMap data");
        }
    }

    private static Map<Date, Long> jsonStringToMapConvert(String servicesOrdered) {
        Gson gson = new Gson().newBuilder().setDateFormat("EEE MMM d H:mm:ss zzz yyyy").create();
        Type type = new TypeToken<Map<Date, Long>>() {
        }.getType();
        return gson.fromJson(servicesOrdered, type);
    }

    private static <T> HashMap<Long, T> createEntityMap(ArrayList<T> entites) {
        HashMap<Long, T> roomHashMap = new HashMap<>();
        Long id = 0L;
        for (T t : entites) {
            roomHashMap.put(id, t);
            id = id + 1;
        }
        return roomHashMap;
    }

    private static List<Object[]> takeListOfObjectsFieldsList(Set<Class<?>> classesToInspect, String entityName) {
        List<Object[]> listOfObjectsFieldsList = new ArrayList<>();
        for (Class<?> clazz : classesToInspect) {
            if (clazz.isAnnotationPresent(ConfigProperty.class)) {
                Annotation annotation = clazz.getAnnotation(ConfigProperty.class);
                String configFileName = ((ConfigProperty) annotation).configFileName();
                if (configFileName.equals(entityName)) {
                    Field[] fields = clazz.getDeclaredFields();
                    List<List<Object>> listOArraysOfTheSameParameters = new ArrayList<>();
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(FieldProperty.class)) {
                            List<Object> listOfCatsedParameters = getListOArraysOfTheSameParameters(entityName, field.getName(), field.getType());
                            listOArraysOfTheSameParameters.add(listOfCatsedParameters);
                        }
                    }
                    listOfObjectsFieldsList = transformToListOfObjectsFieldsList(listOArraysOfTheSameParameters);
                }
            }
        }
        return listOfObjectsFieldsList;
    }

    private static List<Object[]> transformToListOfObjectsFieldsList(List<List<Object>> listOArraysOfTheSameParameters) {
        List<Object[]> transformedList = new ArrayList<>();
        int rowCount = listOArraysOfTheSameParameters.get(0).size();
        for (int i = 0; i < rowCount; i++) {
            Object[] newRow = new String[listOArraysOfTheSameParameters.size()];
            for (int j = 0; j < listOArraysOfTheSameParameters.size(); j++) {
                newRow[j] = listOArraysOfTheSameParameters.get(j).get(i);
            }
            transformedList.add(newRow);
        }
        return transformedList;
    }

    private static List<Object> getListOArraysOfTheSameParameters(String entityName, String fieldName, Class<?> type) {
        String IMPORT_PATH = "\\betterthenspring\\resources";
        List<Object> listOfCastedObjects = new ArrayList<>();
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + IMPORT_PATH + "\\" + entityName + ".properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String propertyValue = properties.getProperty(fieldName);
        if (propertyValue != null) {
            listOfCastedObjects = convertToLIstOfCastedObjects(propertyValue, (Class<Object>) type);
        } else {
            System.out.println("Property not found: " + fieldName);
        }
        return listOfCastedObjects;
    }

    private static List<Object> convertToLIstOfCastedObjects(String propertyValue, Class<Object> type) {
        List<Object> listOfObject = new ArrayList<>();
        for (String o : propertyValue.split(",")) {
            listOfObject.add(o);
        }
        return listOfObject;
    }

    public static void fillDataToMapFromPropertiesFile() {
        Set<Class<?>> scannedClasses = Scanner.classesScan();
        HashMap<String, Object> instances = Container.getInstances();
        findMapToBeFilled(scannedClasses, instances);
    }
}
