package com.epam.jwd.service.converter.impl;

import com.epam.jwd.dao.api.BookDao;
import com.epam.jwd.dao.entity.book.Book;
import com.epam.jwd.dao.entity.order.Order;
import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.dao.impl.BookDaoImpl;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.dto.orderdto.OrderDto;


public class OrderConverter implements Converter<Order, OrderDto, Integer> {

    private final BookDao bookDao = BookDaoImpl.getInstance();
    private final Converter<Book, BookDto, Integer> bookConverter = new BookConverter();

    @Override
    public Order convert(OrderDto entity) {
        return new Order.OrderBuilder()
                .withId(entity.getId())
                .withOrderStatus(entity.getOrderStatus())
                .withAccountId(entity.getAccountId())
                .withBookId(entity.getBook().getId())
                .withDateOfIssue(entity.getDateOfIssue())
                .withReturnDate(entity.getReturnDate())
                .withSubscription(entity.isSubscription())
                .build();
    }

    @Override
    public OrderDto convert(Order entity) throws DaoException {
        Book book = null;
        if (bookDao.findById(entity.getBookId()).isPresent()) {
            book = bookDao.findById(entity.getBookId()).get();
        }

        return new OrderDto.OrderDtoBuilder()
                .withId(entity.getId())
                .withOrderStatus(entity.getOrderStatus())
                .withAccountId(entity.getAccountId())
                .withBook(bookConverter.convert(book))
                .withDateOfIssue(entity.getDateOfIssue())
                .withReturnDate(entity.getReturnDate())
                .withSubscription(entity.isSubscription())
                .build();
    }
}
