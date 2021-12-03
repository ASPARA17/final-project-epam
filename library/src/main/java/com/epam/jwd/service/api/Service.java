package com.epam.jwd.service.api;

import com.epam.jwd.service.dto.EntityDto;
import com.epam.jwd.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface Service<T extends EntityDto<K>, K> {
    List<T> findAll() throws ServiceException;
    Optional<T> findById(K id) throws ServiceException;
}
