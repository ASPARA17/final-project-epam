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

    // account table
    public static final String ADD_ACCOUNT = "INSERT INTO accounts(id_user, first_name, " +
            "second_name) VALUES(?,?,?)";
    public static final String UPDATE_PHONE_BY_ID = "UPDATE accounts SET phone = ? WHERE id = ?";
    public static final String FIND_ALL_ACCOUNTS = "SELECT id, id_user, first_name, second_name, " +
            "phone FROM accounts";
    public static final String FIND_ACCOUNT_BY_ID = "SELECT id, id_user, first_name, second_name," +
            " phone FROM accounts WHERE id = ?";
    public static final String FIND_ACCOUNT_BY_USER_ID = "SELECT id, id_user, first_name, " +
            "second_name, phone FROM accounts WHERE id_user = ?";
}
