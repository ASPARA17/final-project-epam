package com.epam.jwd.service.exception;

public class ExceptionMessage {
    private ExceptionMessage() {
    }

    public static final String STR_EMPTY_OR_NULL_EXCEPTION = "String is empty or null";
    public static final String INVALID_PASSWORD_EXCEPTION = "The password must contain from 3 to " +
            "20 characters of any kind";
    public static final String INVALID_LOGIN_EXCEPTION = "The username must contain only english " +
            "letters and numbers";
    public static final String INCORRECT_SIGN_IN_PARAM_EXCEPTION = "Incorrect sign in parameters";
    public static final String SERVICE_SIGN_IN_METHOD_EXCEPTION = "Sign in operation was failed";
    public static final String SERVICE_CREATE_METHOD_EXCEPTION = "Create operation was failed";
    public static final String SERVICE_FIND_ALL_METHOD_EXCEPTION = "Find all operation was failed";
    public static final String SERVICE_FIND_BY_ID_METHOD_EXCEPTION = "Find by id operation was " +
            "failed";
    public static final String PASSWORD_NOT_CONFIRM_EXCEPTION = "Password mismatch";
    public static final String LOGIN_NOT_UNIQUE_EXCEPTION = "Login is already in use";
    public static final String CHECK_LOGIN_UNIQUE_METHOD_EXCEPTION = "Check login unique " +
            "operation was failed";
    public static final String INVALID_PHONE_EXCEPTION = "Wrong phone number format";
    public static final String INVALID_SECOND_NAME_EXCEPTION = "The surname must contain only " +
            "english letters";
    public static final String INVALID_FIRST_NAME_EXCEPTION = "The name must contain only " +
            "english letters";
    public static final String INVALID_SUBSCRIPTION_ID_EXCEPTION = "The subscription id must " +
            "contain only 6 digits or empty string";
    public static final String SERVICE_UPDATE_METHOD_EXCEPTION = "Update operation was failed";
    public static final String INVALID_QUANTITY_EXCEPTION = "The quantity must contain only digits";
    public static final String INVALID_PAGES_EXCEPTION = "The pages must contain only digits";
    public static final String INVALID_YEAR_EXCEPTION = "The year must contain only digits";
    public static final String INVALID_PUBLISHER_EXCEPTION = "The publisher must contain only " +
            "english letters and symbols \"&-/,.\"";
    public static final String INVALID_NAME_BOOK_EXCEPTION = "The name must contain from 1 to 100" +
            " characters of any kind";
    public static final String INVALID_AUTHOR_EXCEPTION = "The author's name must contain only" +
            " english letters";
    public static final String SERVICE_SORT_METHOD_EXCEPTION = "Sort operation was failed";



}
