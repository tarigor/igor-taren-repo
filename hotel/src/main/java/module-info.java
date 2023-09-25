module hotel {
    exports com.senla.hotel.service.impl;
    exports com.senla.hotel.dao.impl;
    exports com.senla.hotel.entity;
    exports com.senla.hotel.dto;
    exports com.senla.hotel.constant;
    requires lombok;
    requires com.google.gson;
    requires annotations;
}