package com.senla.menu.exception;

public class CommonExceptionHotelUIModule extends Exception {
    public CommonExceptionHotelUIModule() {
        super();
    }

    public CommonExceptionHotelUIModule(String message) {
        super(message);
    }

    public CommonExceptionHotelUIModule(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonExceptionHotelUIModule(Throwable cause) {
        super(cause);
    }

    protected CommonExceptionHotelUIModule(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
