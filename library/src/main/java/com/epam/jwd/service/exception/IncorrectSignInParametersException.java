package com.epam.jwd.service.exception;

public class IncorrectSignInParametersException extends Exception {
    public IncorrectSignInParametersException() {
    }

    public IncorrectSignInParametersException(String message) {
        super(message);
    }

    public IncorrectSignInParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectSignInParametersException(Throwable cause) {
        super(cause);
    }

    public IncorrectSignInParametersException(String message, Throwable cause,
                                              boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
