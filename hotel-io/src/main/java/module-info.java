module hotel.io {
    exports com.senla.hotelio.service.entityimport;
    exports com.senla.hotelio.service.entityexport.impl;
    exports com.senla.hotelio.service.entityimport.impl;
    requires com.google.gson;
    requires hotel;
    requires annotations;
    opens com.senla.hotelio.service.entityexport.impl to betterthenspring;
}