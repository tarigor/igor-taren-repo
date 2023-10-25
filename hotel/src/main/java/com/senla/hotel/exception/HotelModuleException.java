package com.senla.hotel.exception;

public class HotelModuleException extends Exception {
    public HotelModuleException() {
        super();
    }

    public HotelModuleException(String message) {
        super(message);
    }

    public HotelModuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelModuleException(Throwable cause) {
        super(cause);
    }

    protected HotelModuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
