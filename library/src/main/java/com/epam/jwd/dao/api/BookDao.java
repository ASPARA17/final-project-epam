package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.Book;
import com.epam.jwd.dao.exception.DaoException;

import java.util.List;

public interface BookDao extends BaseDao<Book, Integer> {
    List<Book> findByName(String name) throws DaoException;
    void updateQuantityById(Integer id) throws DaoException;
    List<Book> findByGenre(String genre) throws DaoException;
    List<Book> findByAuthor(String author) throws DaoException;
}
