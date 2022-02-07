package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.BookDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.book.Book;
import com.epam.jwd.dao.entity.book.Genre;
import com.epam.jwd.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {
    private static BookDaoImpl instance = new BookDaoImpl();
    private ConnectionPool pool;

    private static final Logger log = LogManager.getLogger(BookDaoImpl.class);

    private BookDaoImpl() {
        this.pool = ConnectionPoolImpl.getInstance();
    }


    public static BookDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Book add(Book book) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_BOOK,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, book.getGenre().getGenreId());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getName());
            statement.setString(4, book.getPublishingHouse());
            statement.setInt(5, book.getYearPublishing());
            statement.setInt(6, book.getNumberOfPage());
            statement.setInt(7, book.getQuantity());
            statement.executeUpdate();
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
            allBooks = findBooks(statement);
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
    public List<Book> findByGenre(String genreName) throws DaoException {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_GENRE)) {
            statement.setString(1, genreName);
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

    @Override
    public List<Book> findBooksToPage(int page, int totalBookOnPage) throws DaoException{
        List<Book> booksOnPage;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_BOOKS_ON_PAGE)) {
            statement.setInt(1, page - 1);
            statement.setInt(2, totalBookOnPage);
            booksOnPage = findBooks(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        log.info(booksOnPage);
        return booksOnPage;
    }

    private List<Book> findBooks(PreparedStatement statement) throws SQLException {
        List<Book> allBooks = new ArrayList<>();
        try (statement; ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                allBooks.add(createBook(resultSet));
            }
        }
        return allBooks;
    }

    private Book createBook(ResultSet resultSet) throws SQLException{
        return new Book.BookBuilder()
                .withId(resultSet.getInt(1))
                .withGenre(Genre.getGenreById(resultSet.getInt(2)))
                .withAuthor(resultSet.getString(3))
                .withName(resultSet.getString(4))
                .withPublishingHouse(resultSet.getString(5))
                .withYearPublishing(resultSet.getInt(6))
                .withNumberOfPage(resultSet.getInt(7))
                .withQuantity(resultSet.getInt(8))
                .build();
    }
}
