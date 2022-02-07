package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.orderdto.OrderDto;
import com.epam.jwd.service.exception.ServiceException;

public class OrderValidator implements Validator <OrderDto, Integer> {

    @Override
    public boolean validate(OrderDto entity) throws ServiceException {
        return false;
    }

}
