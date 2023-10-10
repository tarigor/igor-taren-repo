module hotel.serialization {
    requires annotations;
    requires hotel;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
    exports com.serialization;
}