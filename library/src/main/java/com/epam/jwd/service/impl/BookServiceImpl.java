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
import com.epam.jwd.service.validator.book.BookValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.jwd.service.exception.ExceptionMessage.*;

// TODO replace BookServiceImpl->BookService may be
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final Converter<Book, BookDto, Integer> converter;
    private final BookValidator validator;
    private static BookServiceImpl instance = new BookServiceImpl();
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);
    private static final String NAME_SORT_PARAM = "name";
    private static final String QUANTITY_SORT_PARAM = "quantity";

    private BookServiceImpl() {
        this.bookDao = BookDaoImpl.getInstance();
        this.converter = new BookConverter();
        this.validator = new BookValidator();
    }

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
            log.error(SERVICE_CREATE_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_CREATE_METHOD_EXCEPTION, e);
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
            log.error(SERVICE_FIND_ALL_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_FIND_ALL_METHOD_EXCEPTION, e);
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
            log.error(SERVICE_FIND_ALL_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_FIND_ALL_METHOD_EXCEPTION, e);
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
            log.error(SERVICE_FIND_BY_ID_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_FIND_BY_ID_METHOD_EXCEPTION, e);
        }
        return bookDtoOptional;
    }

    @Override
    public List<BookDto> sortByParameter(List<BookDto> books, String sortParam) throws ServiceException {
        BookNameComparator nameComparator = BookNameComparator.getInstance();
        BookQuantityComparator quantityComparator = BookQuantityComparator.getInstance();

        List<BookDto> sortedBooks = new ArrayList<>(books);
        switch (sortParam) {
            case NAME_SORT_PARAM:
                sortedBooks.sort(nameComparator);
                break;
            case QUANTITY_SORT_PARAM:
                sortedBooks.sort(quantityComparator);
                break;
            default:
                log.error(SERVICE_SORT_METHOD_EXCEPTION);
                throw new ServiceException(SERVICE_SORT_METHOD_EXCEPTION);
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
            log.error(SERVICE_FIND_BY_ID_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_FIND_BY_ID_METHOD_EXCEPTION, e);
        }
        return foundBooks;
    }

    @Override
    public void updateQuantityById(int quantity, Integer bookId) throws ServiceException {
        validator.isValidateQuantity(Integer.toString(quantity));
        try {
            bookDao.updateQuantityById(quantity, bookId);
        } catch (DaoException e) {
            log.error(SERVICE_UPDATE_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_UPDATE_METHOD_EXCEPTION, e);
        }
    }

    @Override
    public void editBook(BookDto book, Integer bookId) throws ServiceException {
        validator.validate(book);
        try {
            bookDao.updateBookById(converter.convert(book), bookId);
        } catch (DaoException e) {
            log.error(SERVICE_UPDATE_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_UPDATE_METHOD_EXCEPTION, e);
        }
    }
}
