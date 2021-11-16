package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.User;

public interface UserDao extends BaseDao<User, Integer> {
    void createAccount(User user);
    String findPasswordByLogin(String login);
    void updatePasswordByLogin(String login);
}
