package com.epam.jwd.dao.impl;

public class SqlQuery {
    private SqlQuery() {}
    // users table
    public static final String ADD_USER = "INSERT INTO users(login, password, id_role) VALUES(?," +
            "?,?)";
    public static final String FIND_ALL_USERS = "SELECT id, login, password, id_role FROM users " +
            "WHERE id_role = ?";
}
