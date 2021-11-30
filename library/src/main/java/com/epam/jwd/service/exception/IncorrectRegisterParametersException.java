package com.epam.jwd.service.exception;

public class IncorrectRegisterParametersException extends Exception {
    public IncorrectRegisterParametersException() {
    }

    public IncorrectRegisterParametersException(String message) {
        super(message);
    }

    public IncorrectRegisterParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectRegisterParametersException(Throwable cause) {
        super(cause);
    }

    public IncorrectRegisterParametersException(String message, Throwable cause,
                                                boolean enableSuppression,
                                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
