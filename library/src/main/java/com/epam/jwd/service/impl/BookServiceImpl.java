package com.epam.jwd.service.impl;

import com.epam.jwd.dao.api.BookDao;
import com.epam.jwd.dao.entity.book.Book;
import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.dao.impl.BookDaoImpl;
import com.epam.jwd.service.api.BookService;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.converter.impl.BookConverter;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.BookValidator;
import com.epam.jwd.service.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final Converter<Book, BookDto, Integer> converter;
    private final Validator<BookDto, Integer> validator;
    private static volatile BookServiceImpl instance;

    private BookServiceImpl() {
        this.bookDao = BookDaoImpl.getInstance();
        this.converter = new BookConverter();
        this.validator = new BookValidator();
    }

    public static BookServiceImpl getInstance() {
        BookServiceImpl localInstance = instance;
        if (instance == null) {
            synchronized (BookServiceImpl.class) {
                localInstance = instance;
                if (instance == null) {
                    instance = localInstance = new BookServiceImpl();
                }
            }
        }
        return  localInstance;
    }

    @Override
    public BookDto create(BookDto bookDto) throws ServiceException {
        validator.validate(bookDto);
        Book createdBook = converter.convert(bookDto);
        try {
            bookDto = converter.convert(bookDao.add(createdBook));
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return bookDto;
    }

    @Override
    public List<BookDto> findAll() throws ServiceException {
        List<BookDto> bookDto = new ArrayList<>();
        try {
            for (Book book : bookDao.findAll()) {
                bookDto.add(converter.convert(book));
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return bookDto;
    }

    @Override
    public Optional<BookDto> findById(Integer id) throws ServiceException {
        Optional<BookDto> bookDtoOptional = Optional.empty();
        try {
            Optional<Book> book = bookDao.findById(id);
            if (book.isPresent()) {
                BookDto bookDto = converter.convert(book.get());
                bookDtoOptional = Optional.of(bookDto);
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return bookDtoOptional;
    }
}
