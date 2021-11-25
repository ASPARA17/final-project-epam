package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.BookDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.book.Book;
import com.epam.jwd.dao.entity.book.Genre;
import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.dao.entity.user.UserRole;
import com.epam.jwd.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {
    private static volatile BookDaoImpl instance;
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private BookDaoImpl() {
    }

    public static BookDaoImpl getInstance() {
        BookDaoImpl localInstance = instance;
        if (instance == null) {
            synchronized (BookDaoImpl.class) {
                localInstance = instance;
                if (instance == null) {
                    instance = localInstance = new BookDaoImpl();
                }
            }
        }
        return  localInstance;
    }

    @Override
    public Book add(Book book) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_BOOK)) {
            statement.setInt(1, book.getGenre().getGenreId());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getName());
            statement.setString(4, book.getPublishingHouse());
            statement.setInt(5, book.getYearPublishing());
            statement.setInt(6, book.getNumberOfPage());
            statement.setInt(7, book.getQuantity());
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                book.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return book;
    }

    @Override
    public boolean delete(Book book) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_BOOK_BY_ID)) {
            statement.setInt(1, book.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> allBooks = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_BOOKS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allBooks.add(createBook(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return allBooks;
    }

    @Override
    public Optional<Book> findById(Integer id) throws DaoException {
        Optional<Book> book = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = Optional.of(createBook(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return book;
    }

    @Override
    public List<Book> findByName(String name) throws DaoException {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookList.add(createBook(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return bookList;
    }

    @Override
    public void updateQuantityById(int quantity, Integer id) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_QUANTITY_BY_ID)) {
            statement.setInt(1, quantity);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> findByGenre(int genreId) throws DaoException {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_GENRE)) {
            statement.setInt(1, genreId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookList.add(createBook(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return bookList;
    }

    @Override
    public List<Book> findByAuthor(String author) throws DaoException {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_AUTHOR)) {
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookList.add(createBook(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return bookList;
    }

    private Book createBook(ResultSet resultSet) throws SQLException{
        int genreId = resultSet.getInt(2);
        return new Book(resultSet.getInt(1),
                Genre.getGenreById(genreId),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getInt(6),
                resultSet.getInt(7),
                resultSet.getInt(8));
    }
}
