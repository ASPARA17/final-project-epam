package com.epam.jwd.dao.impl;

public class SqlQuery {
    private SqlQuery() {}
    // users table
    public static final String ADD_USER = "INSERT INTO users(login, password, id_role) VALUES(?," +
            "?,?)";
    public static final String FIND_ALL_USERS = "SELECT id, login, password, id_role FROM users " +
            "WHERE id_role = ?";
    public static final String FIND_USER_BY_ID = "SELECT id, login, password FROM users " +
            "WHERE id = ?";
    public static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login =" +
            " ?";
    public static final String UPDATE_PASSWORD_BY_LOGIN = "UPDATE users SET password = ? WHERE " +
            "login = ?";

    // books table
    public static final String ADD_BOOK = "INSERT INTO books(id_genre, author, name, " +
            "publishing_house, year_publishing, number_of_page, quantity) VALUES(?,?,?,?,?,?,?)";
    public static final String DELETE_BOOK_BY_ID = "DELETE FROM books WHERE id = ?";
    public static final String FIND_ALL_BOOKS = "SELECT id, id_genre, author, name, " +
            "publishing_house, year_publishing, number_of_page, quantity FROM books";
    public static final String FIND_BOOK_BY_ID = "SELECT id, id_genre, author, name, " +
            "publishing_house, year_publishing, number_of_page, quantity FROM books WHERE id = ?";
    public static final String FIND_BOOK_BY_NAME = "SELECT id, id_genre, author, name, " +
            "publishing_house, year_publishing, number_of_page, quantity FROM books WHERE name = ?";
    public static final String UPDATE_QUANTITY_BY_ID = "UPDATE books SET quantity = ? WHERE id = ?";
    public static final String FIND_BOOK_BY_GENRE = "SELECT id, id_genre, author, name, " +
            "publishing_house, year_publishing, number_of_page, quantity FROM books WHERE genre=?";
    public static final String FIND_BOOK_BY_AUTHOR = "SELECT id, id_genre, author, name, " +
            "publishing_house, year_publishing, number_of_page, quantity FROM books WHERE author=?";
}
