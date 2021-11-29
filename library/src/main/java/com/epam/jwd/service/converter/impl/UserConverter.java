package com.epam.jwd.service.converter.impl;

import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.dto.userdto.UserDto;

public class UserConverter implements Converter<User, UserDto, Integer> {
    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
