package com.epam.jwd.service.exception;

public class LoginNotUniqueException extends Exception {
    public LoginNotUniqueException() {
    }

    public LoginNotUniqueException(String message) {
        super(message);
    }

    public LoginNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginNotUniqueException(Throwable cause) {
        super(cause);
    }

    public LoginNotUniqueException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
