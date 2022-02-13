package com.epam.jwd.service.validator.user;

import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.Validator;

import static com.epam.jwd.service.exception.ExceptionMessage.*;

public class UserValidator implements Validator<UserDto, Integer> {
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_]{3,20}$";
    private static final String PASSWORD_REGEX = "^.{3,20}$";


    @Override
    public void validate(UserDto dto) throws ServiceException {
        isValidateLogin(dto.getLogin());
        isValidatePassword(dto.getPassword());
    }

    private void isValidateLogin(String login) throws ServiceException {
        isEmptyOrNull(login);
        if (!login.matches(LOGIN_REGEX)) {
            throw new ServiceException(INVALID_LOGIN_EXCEPTION);
        }
    }

    private void isValidatePassword(String password) throws ServiceException {
        isEmptyOrNull(password);
        if (!password.matches(PASSWORD_REGEX)) {
            throw new ServiceException(INVALID_PASSWORD_EXCEPTION);
        }
    }

    private void isEmptyOrNull(String str) throws ServiceException {
        if (str != null && !str.isEmpty()) {
            throw new ServiceException(STR_EMPTY_OR_NULL_EXCEPTION);
        }
    }

}

