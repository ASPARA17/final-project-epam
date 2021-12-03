package com.epam.jwd.service.api;

import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;

public interface BookService extends Service<BookDto, Integer> {
    BookDto create(BookDto bookDto) throws ServiceException;
}
