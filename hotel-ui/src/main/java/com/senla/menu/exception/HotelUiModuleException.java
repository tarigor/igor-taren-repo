package com.senla.menu.exception;

public class HotelUiModuleException extends Exception {
    public HotelUiModuleException() {
        super();
    }

    public HotelUiModuleException(String message) {
        super(message);
    }

    public HotelUiModuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelUiModuleException(Throwable cause) {
        super(cause);
    }

    protected HotelUiModuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
