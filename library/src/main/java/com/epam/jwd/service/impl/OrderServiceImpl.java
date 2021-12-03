package com.epam.jwd.service.impl;

import com.epam.jwd.service.api.OrderService;
import com.epam.jwd.service.dto.orderdto.OrderDto;
import com.epam.jwd.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDto create(OrderDto orderDto) throws ServiceException {
        return null;
    }

    @Override
    public List<OrderDto> findAll() throws ServiceException {
        return null;
    }

    @Override
    public Optional<OrderDto> findById(Integer id) throws ServiceException {
        return Optional.empty();
    }
}
