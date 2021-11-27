package com.epam.jwd.service.impl;

import com.epam.jwd.service.api.OrderService;
import com.epam.jwd.service.dto.orderdto.OrderDto;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDto add(OrderDto entity) {
        return null;
    }

    @Override
    public List<OrderDto> findAll() {
        return null;
    }

    @Override
    public Optional<OrderDto> findById(Integer id) {
        return Optional.empty();
    }
}
