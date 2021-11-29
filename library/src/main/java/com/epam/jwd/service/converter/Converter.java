package com.epam.jwd.service.converter;

import com.epam.jwd.dao.entity.Entity;
import com.epam.jwd.service.dto.EntityDto;

public interface Converter<T extends Entity<K>, V extends EntityDto<K>, K> {
    T convert(V entity);
    V convert(T entity);
}
