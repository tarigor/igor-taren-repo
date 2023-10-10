module hotel {
    exports com.senla.hotel.service.impl;
    exports com.senla.hotel.dao.impl;
    exports com.senla.hotel.entity;
    exports com.senla.hotel.dto;
    exports com.senla.hotel.constant;
    requires annotations;
    opens com.senla.hotel.entity to com.google.gson;
    requires java.sql;
    requires hotel.db;
    requires lombok;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;
    exports com.senla.hotel.deserializationService;
    exports com.senla.hotel.dao;
}