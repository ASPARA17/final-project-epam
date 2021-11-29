package com.epam.jwd.service.impl;

import com.epam.jwd.dao.api.UserDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.converter.impl.UserConverter;
import com.epam.jwd.service.dto.userdto.UserDto;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Converter<User, UserDto, Integer> converter;
    private static volatile UserServiceImpl instance;

    private UserServiceImpl() {
        this.userDao = UserDaoImpl.getInstance();
        this.converter = new UserConverter();
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
    public UserDto add(UserDto entity) {
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public Optional<UserDto> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> sigInUser(String login, String password) {
        return Optional.empty();
    }

    @Override
    public UserDto registerUser(String login, String password, String confirmPassword,
                                String firstName, String secondName, String phone) {
        return null;
    }
}
