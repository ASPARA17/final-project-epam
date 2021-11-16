package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.Entity;

import java.util.List;

public interface BaseDao<T extends Entity<K>, K> {
    T add(T entity);
    T remove(T entity);
    List<T> findAll();
}
