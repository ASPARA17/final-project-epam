package com.epam.jwd.service.impl;

import com.epam.jwd.service.api.BookService;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    @Override
    public BookDto create(BookDto bookDto) throws ServiceException {
        return null;
    }

    @Override
    public List<BookDto> findAll() throws ServiceException {
        return null;
    }

    @Override
    public Optional<BookDto> findById(Integer id) throws ServiceException {
        return Optional.empty();
    }
}
