package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.ServiceException;

public class UserValidator implements Validator<UserDto, Integer>{
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_]{3,20}$";
    private static final String PASSWORD_REGEX = "^.{3,20}$";

    @Override
    public boolean validate(UserDto dto) throws ServiceException {
        return isValidateLogin(dto.getLogin())
                && isValidatePassword(dto.getPassword());
    }

    private boolean isValidateLogin(String login) throws ServiceException {
        return isEmptyOrNull(login)
                && login.matches(LOGIN_REGEX);
    }

    private boolean isValidatePassword(String password) throws ServiceException {
        return isEmptyOrNull(password)
                && password.matches(PASSWORD_REGEX);
    }

    private boolean isEmptyOrNull(String str) {
        return str != null && !str.isEmpty();
    }

}

