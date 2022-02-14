package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.service.api.BookService;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.BookServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.epam.jwd.controller.command.RequestParameterName.ALL_BOOKS;
import static com.epam.jwd.controller.command.RequestParameterName.GENRE_PARAM;

public class FilterBooksCommand implements Command {
    private static final Logger log = LogManager.getLogger(FilterBooksCommand.class);
    private final BookService bookService = BookServiceImpl.getInstance();
    private static final Command instance = new FilterBooksCommand();
    private static final String ERROR_MESSAGE = "Can't filter books";
    private static final String EMPTY_MESSAGE = "Books not found";
    private static final String ERROR_ATTRIBUTE = "error";

    private FilterBooksCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse CATALOG = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.CATALOG_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final CommandResponse ERROR = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.ERROR_404;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        HttpSession session;
        if (request.getCurrentSession().isPresent()) {
            session = request.getCurrentSession().get();
        } else {
            return ERROR;
        }

        try {
            String genreName = request.getParameter(GENRE_PARAM);
            List<BookDto> foundBooks = bookService.findBooksByGenre(genreName);
            if (foundBooks.isEmpty()) {
                log.error(EMPTY_MESSAGE);
                request.setAttribute(ERROR_ATTRIBUTE, EMPTY_MESSAGE);
                return CATALOG;
            }
            session.setAttribute(ALL_BOOKS, foundBooks);
        } catch (ServiceException e) {
            log.error(ERROR_MESSAGE, e);
            request.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
            return CATALOG;
        }
        return CATALOG;
    }
}
