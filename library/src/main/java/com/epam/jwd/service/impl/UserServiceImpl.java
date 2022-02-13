package com.epam.jwd.service.impl;

import com.epam.jwd.dao.api.UserDao;
import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.converter.impl.UserConverter;
import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.*;
import com.epam.jwd.service.validator.user.UserValidator;
import com.epam.jwd.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.jwd.service.exception.ExceptionMessage.*;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Converter<User, UserDto, Integer> converter;
    private final Validator<UserDto, Integer> validator;
    private static UserServiceImpl instance = new UserServiceImpl();
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);


    private UserServiceImpl() {
        this.userDao = UserDaoImpl.getInstance();
        this.converter = new UserConverter();
        this.validator = new UserValidator();
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<UserDto> signInUser(String login, String password) throws ServiceException, IncorrectSignInParametersException {
        Optional<UserDto> userDtoOptional;
        try {
            Optional<User> signInUser = userDao.findByLogin(login);
            String userPassword = userDao.findPasswordByLogin(login);

            if (password.equals(userPassword) && signInUser.isPresent()) {
                UserDto userDto = converter.convert(signInUser.get());
                userDtoOptional = Optional.of(userDto);
            } else {
                throw new IncorrectSignInParametersException(INCORRECT_SIGN_IN_PARAM_EXCEPTION);
            }
        } catch (DaoException e) {
            log.error(SERVICE_SIGN_IN_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_SIGN_IN_METHOD_EXCEPTION, e);
        }
        return userDtoOptional;
    }

    @Override
    public UserDto create(UserDto userDto, String confirmPassword) throws ServiceException,
            LoginNotUniqueException, PasswordNotConfirmedException {
        validator.validate(userDto);
        User createdUser = converter.convert(userDto);
        try {
            User user = converter.convert(userDto);
            checkLoginUnique(user.getLogin());
            verifyPassword(user.getPassword(), confirmPassword);
            userDto = converter.convert(userDao.add(createdUser));
        } catch (DaoException e) {
            log.error(SERVICE_CREATE_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_CREATE_METHOD_EXCEPTION, e);
        }
        return userDto;
    }

    @Override
    public List<UserDto> findAll() throws ServiceException {
        List<UserDto> usersDto = new ArrayList<>();
        try {
            for (User user : userDao.findAll()) {
                usersDto.add(converter.convert(user));
            }
        } catch (DaoException e) {
            log.error(SERVICE_FIND_ALL_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_FIND_ALL_METHOD_EXCEPTION, e);
        }
        return usersDto;
    }

    @Override
    public Optional<UserDto> findById(Integer id) throws ServiceException {
        Optional<UserDto> userDtoOptional = Optional.empty();
        try {
           Optional<User> user = userDao.findById(id);
           if (user.isPresent()) {
               UserDto userDto = converter.convert(user.get());
               userDtoOptional = Optional.of(userDto);
           }
        } catch (DaoException e) {
            log.error(SERVICE_FIND_BY_ID_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_FIND_BY_ID_METHOD_EXCEPTION, e);
        }
        return userDtoOptional;
    }

    private void verifyPassword(String password, String confirmPassword) throws PasswordNotConfirmedException {
        if (!password.equals(confirmPassword)) {
            throw new PasswordNotConfirmedException(PASSWORD_NOT_CONFIRM_EXCEPTION);
        }
    }

    private void checkLoginUnique(String login) throws LoginNotUniqueException, ServiceException {
        try {
            Optional<User> foundUser = userDao.findByLogin(login);
            if (foundUser.isPresent()) {
                log.error(LOGIN_NOT_UNIQUE_EXCEPTION);
                throw new LoginNotUniqueException(LOGIN_NOT_UNIQUE_EXCEPTION);
            }
        } catch (DaoException e) {
            log.error(CHECK_LOGIN_UNIQUE_METHOD_EXCEPTION, e);
            throw new ServiceException(CHECK_LOGIN_UNIQUE_METHOD_EXCEPTION, e);
        }
    }
}
