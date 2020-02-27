package com.ttnhat.shop.ExceptionHandler.Exception;

public class SQLException extends RuntimeException {
    public SQLException(String message) {
        super(message);
    }

    public SQLException(String message, Throwable cause) {
        super(message, cause);
    }
}
