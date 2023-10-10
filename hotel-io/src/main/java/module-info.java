module hotel.io {
    exports com.senla.hotelio.service.entityimport;
    exports com.senla.hotelio.service.entityexport.impl;
    exports com.senla.hotelio.service.entityimport.impl;
    requires hotel;
    requires annotations;
    requires org.slf4j;
    opens com.senla.hotelio.service.entityexport.impl to betterthenspring;
}