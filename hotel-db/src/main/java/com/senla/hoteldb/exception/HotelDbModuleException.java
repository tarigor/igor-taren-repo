package com.senla.hoteldb.exception;

public class HotelDbModuleException extends Exception {
    public HotelDbModuleException() {
        super();
    }

    public HotelDbModuleException(String message) {
        super(message);
    }

    public HotelDbModuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelDbModuleException(Throwable cause) {
        super(cause);
    }

    protected HotelDbModuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
