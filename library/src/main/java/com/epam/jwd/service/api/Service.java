package com.epam.jwd.service.api;

import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.service.dto.EntityDto;

import java.util.List;
import java.util.Optional;

public interface Service<T extends EntityDto<K>, K> {
    T add(T entity);
    List<T> findAll();
    Optional<T> findById(K id);
}
