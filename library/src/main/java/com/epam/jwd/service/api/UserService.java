package com.epam.jwd.service.api;

import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.*;

import java.util.Optional;

public interface UserService extends Service<UserDto, Integer> {
    UserDto create(UserDto entity, String confirmPassword) throws ServiceException,
            LoginNotUniqueException, PasswordNotConfirmedException;
    Optional<UserDto> signInUser(String login, String password) throws ServiceException,
            IncorrectSignInParametersException;
}
