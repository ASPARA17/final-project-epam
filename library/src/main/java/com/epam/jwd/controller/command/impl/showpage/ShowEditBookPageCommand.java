package com.epam.jwd.controller.command.impl.showpage;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.BookServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.epam.jwd.controller.command.RequestParameterName.*;

public class ShowEditBookPageCommand implements Command {
    private static final Command instance = new ShowEditBookPageCommand();
    private static final Logger log = LogManager.getLogger(ShowEditBookPageCommand.class);
    private static final BookServiceImpl bookService = BookServiceImpl.getInstance();
    private static final String ERROR_MESSAGE = "Can't find book";
    private static final String ERROR_ATTRIBUTE = "error";

    private ShowEditBookPageCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_EDIT_PAGE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.EDIT_BOOK_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final CommandResponse ERROR_PAGE = new CommandResponse() {
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
            return ERROR_PAGE;
        }

        String bookId = request.getParameter(BOOK_ID);
        try {
            Optional<BookDto> foundBook = bookService.findById(Integer.parseInt(bookId));
            if (foundBook.isPresent()) {
                BookDto book = foundBook.get();

                session.setAttribute(BOOK_AUTHOR, book.getAuthor());
                session.setAttribute(BOOK_NAME, book.getName());
                session.setAttribute(BOOK_PUBLISHER, book.getPublishingHouse());
                session.setAttribute(BOOK_GENRE, book.getGenre().getGenreId());
                session.setAttribute(BOOK_YEAR, book.getYearPublishing());
                session.setAttribute(BOOK_PAGES, book.getNumberOfPage());
                session.setAttribute(BOOK_QUANTITY, book.getQuantity());
                session.setAttribute(BOOK_ID, bookId);
            }
        } catch (ServiceException e) {
            log.error(ERROR_MESSAGE, e);
            request.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
        }
        return SHOW_EDIT_PAGE;
    }
}
