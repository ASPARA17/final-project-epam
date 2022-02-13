package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.EntityDto;
import com.epam.jwd.service.exception.ServiceException;

public interface Validator<T extends EntityDto<K>, K> {
    void validate(T entity) throws ServiceException;
}
