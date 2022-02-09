package com.epam.jwd.service.api;

import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;

import java.util.List;

public interface BookService extends Service<BookDto, Integer> {
    BookDto create(BookDto bookDto) throws ServiceException;
    List<BookDto> findBooksToPage(int page, int totalBookOnPage) throws ServiceException;
    List<BookDto> sortByParameter(List<BookDto> books, String sortParam);
    List<BookDto> findBooksByGenre(String genreName) throws ServiceException;
    void updateQuantityById(int quantity, Integer bookId) throws ServiceException;
    void editBook(BookDto book, Integer bookId) throws ServiceException;

}
