package com.epam.jwd.service.api;

import com.epam.jwd.service.dto.userdto.UserDto;

import java.util.Optional;

public interface UserService extends Service<UserDto, Integer> {
    Optional<UserDto> sigInUser(String login, String password);
    UserDto registerUser(String login, String password, String confirmPassword, String firstName,
                         String secondName, String phone);
}
