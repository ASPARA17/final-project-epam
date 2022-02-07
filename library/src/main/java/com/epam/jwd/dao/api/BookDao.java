package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.book.Book;
import com.epam.jwd.dao.exception.DaoException;

import java.util.List;

public interface BookDao extends BaseDao<Book, Integer> {
    List<Book> findByName(String name) throws DaoException;
    void updateQuantityById(int quantity, Integer id) throws DaoException;
    List<Book> findByGenre(String genreName) throws DaoException;
    List<Book> findByAuthor(String author) throws DaoException;
    List<Book> findBooksToPage(int page, int totalBookOnPage) throws DaoException;
}
