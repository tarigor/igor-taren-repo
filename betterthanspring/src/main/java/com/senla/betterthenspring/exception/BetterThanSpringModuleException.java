package com.senla.betterthenspring.exception;

public class BetterThanSpringModuleException extends Exception {
    public BetterThanSpringModuleException() {
        super();
    }

    public BetterThanSpringModuleException(String message) {
        super(message);
    }

    public BetterThanSpringModuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public BetterThanSpringModuleException(Throwable cause) {
        super(cause);
    }

    protected BetterThanSpringModuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
