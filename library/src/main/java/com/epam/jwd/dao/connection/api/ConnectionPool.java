package com.epam.jwd.dao.connection.api;

import java.sql.Connection;

public interface ConnectionPool {
    void init();
    void destroy();
    Connection takeConnection();
    void returnConnection(Connection connection);
}
