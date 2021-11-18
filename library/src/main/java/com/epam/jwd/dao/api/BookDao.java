package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.Book;

import java.util.List;

public interface BookDao extends BaseDao<Book, Integer> {
    List<Book> findByName(String name);
    void updateQuantityById(Integer id);
    List<Book> findByGenre(String genre);
    List<Book> findByAuthor(String author);
}
