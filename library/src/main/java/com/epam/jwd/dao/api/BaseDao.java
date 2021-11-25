package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.Entity;
import com.epam.jwd.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends Entity<K>, K> {
    T add(T entity) throws DaoException;
    boolean delete(T entity) throws DaoException;
    List<T> findAll()throws DaoException;
    Optional<T> findById(K id)throws DaoException;
}
