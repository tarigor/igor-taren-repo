module hotel.serialization {
    requires annotations;
    requires hotel;
    exports com.serialization;
    requires com.google.gson;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
}