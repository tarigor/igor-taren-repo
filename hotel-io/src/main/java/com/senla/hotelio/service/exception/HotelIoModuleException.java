package com.senla.hotelio.service.exception;

public class HotelIoModuleException extends Exception {
    public HotelIoModuleException() {
        super();
    }

    public HotelIoModuleException(String message) {
        super(message);
    }

    public HotelIoModuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelIoModuleException(Throwable cause) {
        super(cause);
    }

    protected HotelIoModuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
