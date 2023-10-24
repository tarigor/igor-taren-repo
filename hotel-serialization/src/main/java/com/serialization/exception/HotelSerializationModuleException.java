package com.serialization.exception;

public class HotelSerializationModuleException extends Exception {
    public HotelSerializationModuleException() {
        super();
    }

    public HotelSerializationModuleException(String message) {
        super(message);
    }

    public HotelSerializationModuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelSerializationModuleException(Throwable cause) {
        super(cause);
    }

    protected HotelSerializationModuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
