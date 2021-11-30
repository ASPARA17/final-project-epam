package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.ServiceException;

public class UserValidator {
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_]{3,20}$";
    private static final String PASSWORD_REGEX = "^.{3,20}$";
    private static final String FIRST_NAME_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String SECOND_NAME_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String PHONE_REGEX = "^\\+375(\\s+)?\\(?(25|29|33|44)\\)?(\\s+)" +
            "?[0-9]{3}-?[0-9]{2}-?[0-9]{2}$";

    public boolean validateUser(UserDto dto) throws ServiceException {
        return isValidateLogin(dto.getLogin())
                && isValidatePassword(dto.getPassword());
    }

    public boolean validateAccount(AccountDto dto) throws ServiceException {
        return isValidateFirstName(dto.getFirstName())
                && isValidateSecondName(dto.getSecondName())
                && isValidatePhone(dto.getPhone());
    }

    private boolean isValidateLogin(String login) throws ServiceException {
        return isEmptyOrNull(login)
                && login.matches(LOGIN_REGEX);
    }

    private boolean isValidatePassword(String password) throws ServiceException {
        return isEmptyOrNull(password)
                && password.matches(PASSWORD_REGEX);
    }

    private boolean isValidateFirstName(String firstName) throws ServiceException {
        return isEmptyOrNull(firstName)
                && firstName.matches(FIRST_NAME_REGEX);
    }

    private boolean isValidateSecondName(String secondName) throws ServiceException {
        return isEmptyOrNull(secondName)
                && secondName.matches(SECOND_NAME_REGEX);
    }

    private boolean isValidatePhone(String phone) throws ServiceException {
        return isEmptyOrNull(phone)
                && phone.matches(PHONE_REGEX);
    }

    public boolean isValidateRegisterParam(String login, String password, String firstName,
                                           String secondName, String phone) throws ServiceException {
        return isValidateLogin(login)
                && isValidatePassword(password)
                && isValidateFirstName(firstName)
                && isValidateSecondName(secondName)
                && isValidatePhone(phone);
    }

    private boolean isEmptyOrNull(String str) {
        return str != null && !str.isEmpty();
    }


}

