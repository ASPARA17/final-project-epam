package com.epam.jwd.service.api;

import com.epam.jwd.service.dto.orderdto.OrderDto;
import com.epam.jwd.service.exception.ServiceException;

public interface OrderService extends Service<OrderDto, Integer> {
    OrderDto create(OrderDto orderDto) throws ServiceException;
}
