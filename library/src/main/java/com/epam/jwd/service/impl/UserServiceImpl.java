package com.epam.jwd.service.impl;

import com.epam.jwd.dao.api.UserDao;
import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.converter.impl.UserConverter;
import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.LoginNotUniqueException;
import com.epam.jwd.service.exception.PasswordNotConfirmedException;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.UserValidator;
import com.epam.jwd.service.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Converter<User, UserDto, Integer> converter;
    private final Validator<UserDto, Integer> validator;
    private static volatile UserServiceImpl instance;

    private UserServiceImpl() {
        this.userDao = UserDaoImpl.getInstance();
        this.converter = new UserConverter();
        this.validator = new UserValidator();
    }

    public static UserServiceImpl getInstance() {
        UserServiceImpl localInstance = instance;
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                localInstance = instance;
                if (instance == null) {
                    instance = localInstance = new UserServiceImpl();
                }
            }
        }
        return  localInstance;
    }

    @Override
    public UserDto create(UserDto userDto) throws ServiceException, LoginNotUniqueException {
        validator.validate(userDto);
        User createdUser = converter.convert(userDto);
        try {
            checkLoginUnique(createdUser.getLogin());
            userDto = converter.convert(userDao.add(createdUser));
        } catch (DaoException e) {
            throw new ServiceException();
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
            throw new ServiceException();
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
            throw new ServiceException();
        }
        return userDtoOptional;
    }

    public void verifyPassword(String password, String confirmPassword) throws PasswordNotConfirmedException {
        if (!password.equals(confirmPassword)) {
            // TODO: add msg
            throw new PasswordNotConfirmedException();
        }
    }

    private void checkLoginUnique(String login) throws LoginNotUniqueException, ServiceException {
        // TODO: add msg
        try {
            converter.convert(userDao
                    .findByLogin(login)
                    .orElseThrow(LoginNotUniqueException::new));
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
