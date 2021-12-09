package com.epam.jwd.dao.connection.api;

import com.epam.jwd.dao.exception.DaoException;

import java.sql.Connection;

public interface ConnectionPool {
    void init();
    void destroy() throws DaoException;
    Connection takeConnection();
    void returnConnection(Connection connection);
}
