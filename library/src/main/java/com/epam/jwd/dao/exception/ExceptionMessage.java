package com.epam.jwd.dao.exception;

public class ExceptionMessage {
    private ExceptionMessage() {
    }

    public static final String CREATE_EXCEPTION = "Entity creating was failed";
    public static final String FIND_ALL_EXCEPTION = "Find all entities was failed";
    public static final String FIND_BY_ID_EXCEPTION = "Find entity with current id was failed";
    public static final String UPDATE_EXCEPTION = "Entity updating was failed";
    public static final String DELETE_EXCEPTION = "Entity deleting was failed";
    public static final String CONNECTION_EXCEPTION = "Connection failed";
    public static final String INTERRUPT_EXCEPTION = "Current thread was interrupted";

}
