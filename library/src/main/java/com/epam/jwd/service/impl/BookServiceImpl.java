package com.epam.jwd.service.impl;

import com.epam.jwd.service.api.BookService;
import com.epam.jwd.service.dto.bookdto.BookDto;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    @Override
    public BookDto add(BookDto entity) {
        return null;
    }

    @Override
    public List<BookDto> findAll() {
        return null;
    }

    @Override
    public Optional<BookDto> findById(Integer id) {
        return Optional.empty();
    }
}
