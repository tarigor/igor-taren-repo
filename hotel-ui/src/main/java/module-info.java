module hotel.ui {
    exports com.senla.menu;
    requires annotations;
    requires hotel;
    requires hotel.io;
    requires lombok;
    requires betterthenspring;
    requires hotel.serialization;
    requires hotel.db;
    exports com.senla.menu.navigator;
    exports com.senla.menu.service;
    exports com.senla.menu.builder;
    exports com.senla.menu.controller;
    opens com.senla.menu to betterthenspring;
    exports com.senla.menu.action.impl;
    exports com.senla.menu.action;
}