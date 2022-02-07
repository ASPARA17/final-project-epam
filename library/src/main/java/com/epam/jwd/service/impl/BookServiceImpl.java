package com.epam.jwd.service.impl;

import com.epam.jwd.dao.api.BookDao;
import com.epam.jwd.dao.entity.book.Book;
import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.dao.impl.BookDaoImpl;
import com.epam.jwd.service.api.BookService;
import com.epam.jwd.service.comparator.BookNameComparator;
import com.epam.jwd.service.comparator.BookQuantityComparator;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.converter.impl.BookConverter;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.BookValidator;
import com.epam.jwd.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final Converter<Book, BookDto, Integer> converter;
    private final Validator<BookDto, Integer> validator;
    private static BookServiceImpl instance = new BookServiceImpl();

    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);

    private BookServiceImpl() {
        this.bookDao = BookDaoImpl.getInstance();
        this.converter = new BookConverter();
        this.validator = new BookValidator();
    }

    //todo maybe replace
    public static BookServiceImpl getInstance() {
        return instance;
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
    public List<BookDto> findBooksToPage(int page, int totalBookOnPage) throws ServiceException {
        List<BookDto> bookDtoOnPage = new ArrayList<>();
        try {
            for (Book book : bookDao.findBooksToPage(page, totalBookOnPage)) {
                bookDtoOnPage.add(converter.convert(book));
            }
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException();
        }
        return bookDtoOnPage;
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

    @Override
    public List<BookDto> sortByParameter(List<BookDto> books, String sortParam) {
        BookNameComparator nameComparator = BookNameComparator.getInstance();
        BookQuantityComparator quantityComparator = BookQuantityComparator.getInstance();

        List<BookDto> sortedBooks = new ArrayList<>(books);
        switch (sortParam) {
            case "name":
                sortedBooks.sort(nameComparator);
                break;
            case "quantity":
                sortedBooks.sort(quantityComparator);
                break;
            default:
                //todo image
        }
        return sortedBooks;
    }

    @Override
    public List<BookDto> findBooksByGenre(String genreName) throws ServiceException {
        List<BookDto> foundBooks = new ArrayList<>();
        try {
            for (Book book : bookDao.findByGenre(genreName)) {
                foundBooks.add(converter.convert(book));
            }
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException();
        }
        return foundBooks;
    }

    @Override
    public void updateQuantityById(int quantity, Integer bookId) throws ServiceException {
        try {
            bookDao.updateQuantityById(quantity, bookId);
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException();
        }
    }
}
