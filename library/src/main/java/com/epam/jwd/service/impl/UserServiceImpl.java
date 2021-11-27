package com.epam.jwd.service.impl;

import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.dto.userdto.UserDto;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
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
