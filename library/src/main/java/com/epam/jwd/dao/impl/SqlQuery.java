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
    public static final String FIND_USER_BY_LOGIN = "SELECT id, login, password, id_role FROM " +
            "users " +
            "WHERE login = ?";

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
            "publishing_house, year_publishing, number_of_page, quantity FROM books WHERE " +
            "id_genre = (SELECT id FROM genres WHERE name = ?)";
    public static final String FIND_BOOK_BY_AUTHOR = "SELECT id, id_genre, author, name, " +
            "publishing_house, year_publishing, number_of_page, quantity FROM books WHERE author=?";
    public static final String FIND_ALL_BOOKS_ON_PAGE = "SELECT id, id_genre, author, name, " +
            "publishing_house, year_publishing, number_of_page, quantity FROM books LIMIT ?,?";
    public static final String UPDATE_BOOK = "UPDATE books SET id_genre=?, author=?, name=?, " +
            "publishing_house=?, year_publishing=?, number_of_page=?, quantity=? WHERE id=?";

    // account table
    public static final String ADD_ACCOUNT = "INSERT INTO accounts(id_user, first_name, " +
            "second_name, phone) VALUES(?,?,?,?)";
    public static final String UPDATE_PHONE_BY_ID = "UPDATE accounts SET phone = ? WHERE id = ?";
    public static final String FIND_ALL_ACCOUNTS = "SELECT id, id_user, first_name, second_name, " +
            "phone, id_subscription FROM accounts";
    public static final String FIND_ACCOUNT_BY_ID = "SELECT id, id_user, first_name, second_name," +
            " phone, id_subscription FROM accounts WHERE id = ?";
    public static final String FIND_ACCOUNT_BY_USER_ID = "SELECT id, id_user, first_name, " +
            "second_name, phone, id_subscription FROM accounts WHERE id_user = ?";
    public static final String UPDATE_SUBSCRIPTION_ID_BY_ID = "UPDATE accounts SET " +
            "id_subscription = ? WHERE id = ?";
    public static final String FIND_ALL_ACCOUNTS_ON_PAGE = "SELECT id, id_user, first_name, second_name, " +
            "phone, id_subscription FROM accounts WHERE id_user IN (SELECT id FROM users WHERE " +
            "id_role = 2) LIMIT ?,?";
    public static final String UPDATE_ACCOUNT = "UPDATE accounts SET first_name=?, second_name=?, " +
            "phone=?, id_subscription=? WHERE id=?";

    // library card table
    public static final String ADD_LIBRARY_CARD = "INSERT INTO library_cards(date_of_issue, " +
            "expiration_date) VALUES(?,?)";
    public static final String FIND_LIBRARY_CARD_BY_ID = "SELECT id, date_of_issue, " +
            "expiration_date FROM library_card WHERE id = ?";

    // order table
    public static final String ADD_ORDER = "INSERT INTO orders(id_account, id_book, date_of_issue, " +
            "subscription) VALUES(?,?,?,?)";
    public static final String FIND_ALL_ORDERS = "SELECT id, id_order_status, id_account, " +
            "id_book, date_of_issue, return_date, subscription FROM orders";
    public static final String FIND_ORDER_BY_ID = "SELECT id, id_order_status, id_account, " +
            "id_book, date_of_issue, return_date, subscription FROM orders WHERE id = ?";
    public static final String FIND_ORDER_BY_STATUS = "SELECT id, id_order_status, id_account, " +
            "id_book, date_of_issue, return_date, subscription FROM orders WHERE id_order_status = " +
            "(SELECT id FROM orders_status WHERE name = ?)";
    public static final String UPDATE_STATUS_BY_ID = "UPDATE orders SET id_order_status = ? WHERE" +
            " id = ?";
    public static final String FIND_ALL_BY_ACCOUNT_ID = "SELECT id, id_order_status, id_account, " +
            "id_book, date_of_issue, return_date, subscription FROM orders WHERE id_account = ?";
    public static final String UPDATE_RETURN_DATE_BY_ID = "UPDATE orders SET return_date = ? " +
            "WHERE id = ?";
    public static final String FIND_ALL_ON_PAGE_BY_ACCOUNT_ID = "SELECT id, id_order_status, " +
            "id_account, id_book, date_of_issue, return_date, subscription FROM orders WHERE " +
            "id_account = ? ORDER BY date_of_issue DESC, id DESC LIMIT ?,?";
    public static final String FIND_ALL_ORDERS_ON_PAGE = "SELECT id, id_order_status, id_account," +
            "id_book, date_of_issue, return_date, subscription FROM orders ORDER BY date_of_issue" +
            " DESC, id DESC LIMIT ?,?";


}
