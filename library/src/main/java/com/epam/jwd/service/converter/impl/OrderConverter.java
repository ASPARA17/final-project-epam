package com.epam.jwd.service.converter.impl;

import com.epam.jwd.dao.entity.order.Order;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.dto.orderdto.OrderDto;

public class OrderConverter implements Converter<Order, OrderDto, Integer> {
    @Override
    public Order convert(OrderDto entity) {
        return new Order.OrderBuilder()
                .withId(entity.getId())
                .withOrderStatus(entity.getOrderStatus())
                .withAccountId(entity.getAccountId())
                .withBookId(entity.getBookId())
                .withDateOfIssue(entity.getDateOfIssue())
                .withReturnDate(entity.getReturnDate())
                .withSubscription(entity.isSubscription())
                .build();
    }

    @Override
    public OrderDto convert(Order entity) {
        return new OrderDto.OrderDtoBuilder()
                .withId(entity.getId())
                .withOrderStatus(entity.getOrderStatus())
                .withAccountId(entity.getAccountId())
                .withBookId(entity.getBookId())
                .withDateOfIssue(entity.getDateOfIssue())
                .withReturnDate(entity.getReturnDate())
                .withSubscription(entity.isSubscription())
                .build();
    }
}
