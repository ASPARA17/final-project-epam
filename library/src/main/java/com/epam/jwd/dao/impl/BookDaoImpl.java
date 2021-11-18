package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.BookDao;
import com.epam.jwd.dao.entity.book.Book;
import com.epam.jwd.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {
    @Override
    public Book add(Book entity) throws DaoException {
        return null;
    }

    @Override
    public Book delete(Book entity) throws DaoException {
        return null;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Book> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Book> findByName(String name) throws DaoException {
        return null;
    }

    @Override
    public void updateQuantityById(Integer id) throws DaoException {
        // while nothing
    }

    @Override
    public List<Book> findByGenre(String genre) throws DaoException {
        return null;
    }

    @Override
    public List<Book> findByAuthor(String author) throws DaoException {
        return null;
    }
}
