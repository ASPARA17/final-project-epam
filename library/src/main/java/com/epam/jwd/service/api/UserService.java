package com.epam.jwd.service.api;

import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.IncorrectRegisterParametersException;
import com.epam.jwd.service.exception.LoginNotUniqueException;
import com.epam.jwd.service.exception.ServiceException;

import java.util.Optional;

// TODO: think about importance EntityService
public interface UserService extends Service<UserDto, Integer> {
    UserDto create(UserDto entity) throws ServiceException, LoginNotUniqueException;
    Optional<UserDto> signInUser(String login, String password) throws ServiceException, IncorrectRegisterParametersException;
}
