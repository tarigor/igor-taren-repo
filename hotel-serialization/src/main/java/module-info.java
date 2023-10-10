module hotel.serialization {
    requires annotations;
    requires hotel;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
    requires org.slf4j;
    exports com.serialization;
}