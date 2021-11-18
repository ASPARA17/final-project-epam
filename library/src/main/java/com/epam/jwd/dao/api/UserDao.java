package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.dao.exception.DaoException;

public interface UserDao extends BaseDao<User, Integer> {
    void createAccount(User user) throws DaoException;
    String findPasswordByLogin(String login) throws DaoException;
    void updatePasswordByLogin(String login) throws DaoException;
}
