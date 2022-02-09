package com.epam.jwd.service.converter.impl;

import com.epam.jwd.dao.api.AccountDao;
import com.epam.jwd.dao.api.BookDao;
import com.epam.jwd.dao.entity.book.Book;
import com.epam.jwd.dao.entity.order.Order;
import com.epam.jwd.dao.entity.user.Account;
import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.dao.impl.AccountDaoImpl;
import com.epam.jwd.dao.impl.BookDaoImpl;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.dto.orderdto.OrderDto;
import com.epam.jwd.service.dto.userdto.AccountDto;


public class OrderConverter implements Converter<Order, OrderDto, Integer> {
    private final BookDao bookDao = BookDaoImpl.getInstance();
    private final Converter<Book, BookDto, Integer> bookConverter = new BookConverter();
    private final AccountDao accountDao = AccountDaoImpl.getInstance();
    private final Converter<Account, AccountDto, Integer> accountConverter = new AccountConverter();

    @Override
    public Order convert(OrderDto entity) {
        return new Order.OrderBuilder()
                .withId(entity.getId())
                .withOrderStatus(entity.getOrderStatus())
                .withAccountId(entity.getAccount().getId())
                .withBookId(entity.getBook().getId())
                .withDateOfIssue(entity.getDateOfIssue())
                .withReturnDate(entity.getReturnDate())
                .withSubscription(entity.isSubscription())
                .build();
    }

    @Override
    public OrderDto convert(Order entity) throws DaoException {
        Book book = null;
        Account account = null;
        if (bookDao.findById(entity.getBookId()).isPresent()
            && accountDao.findById(entity.getAccountId()).isPresent()) {
            book = bookDao.findById(entity.getBookId()).get();
            account = accountDao.findById(entity.getAccountId()).get();
        }
        return new OrderDto.OrderDtoBuilder()
                .withId(entity.getId())
                .withOrderStatus(entity.getOrderStatus())
                .withAccount(accountConverter.convert(account))
                .withBook(bookConverter.convert(book))
                .withDateOfIssue(entity.getDateOfIssue())
                .withReturnDate(entity.getReturnDate())
                .withSubscription(entity.isSubscription())
                .build();
    }
}
