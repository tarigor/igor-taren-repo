module hotel {
    exports com.senla.hotel.service.impl;
    exports com.senla.hotel.dao.impl;
    exports com.senla.hotel.entity;
    exports com.senla.hotel.dto;
    exports com.senla.hotel.constant;
    requires lombok;
    requires com.google.gson;
    requires annotations;
    opens com.senla.hotel.entity to com.google.gson;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    exports com.senla.hotel.deserializationService;
}