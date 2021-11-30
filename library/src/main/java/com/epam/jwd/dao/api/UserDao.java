package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.dao.exception.DaoException;

import java.util.Optional;

public interface UserDao extends BaseDao<User, Integer> {
    String findPasswordByLogin(String login) throws DaoException;
    void updatePasswordByLogin(String password, String login) throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
}
