package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends Entity<K>, K> {
    T add(T entity);
    T delete(T entity);
    List<T> findAll();
    Optional<T> findById(K id);
}
