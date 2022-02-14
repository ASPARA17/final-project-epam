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
import java.util.List;

import static com.epam.jwd.controller.command.RequestParameterName.ALL_BOOKS;

public class ShowCatalogCommand implements Command {
    private final BookServiceImpl bookService = BookServiceImpl.getInstance();
    private static final Command instance = new ShowCatalogCommand();
    private static final Logger log = LogManager.getLogger(ShowCatalogCommand.class);
    private static final String PAGE_ATTRIBUTE = "page";
    private static final Integer TOTAL_BOOK_ON_PAGE = 7;
    private static final Integer START_PAGE = 1;
    private static final String ERROR_MESSAGE = "Can't find books";
    private static final String ERROR_ATTRIBUTE = "error";

    private ShowCatalogCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_ALL_BOOKS = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.CATALOG_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final CommandResponse ERROR_CATALOG = new CommandResponse() {
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
            return ERROR_CATALOG;
        }

        int page;
        if (request.getParameter(PAGE_ATTRIBUTE) == null
            || Integer.parseInt(request.getParameter(PAGE_ATTRIBUTE)) <= 1) {
            page = START_PAGE;
        } else {
            page = Integer.parseInt(request.getParameter(PAGE_ATTRIBUTE));
        }

        request.setAttribute(PAGE_ATTRIBUTE, page);

        if (page != 1) {
            page = page - 1;
            page = page * TOTAL_BOOK_ON_PAGE + 1;
        }

        try {
            List<BookDto> allBooks = bookService.findBooksToPage(page, TOTAL_BOOK_ON_PAGE);
            session.setAttribute(ALL_BOOKS, allBooks);
        } catch (ServiceException e) {
            log.error(ERROR_MESSAGE, e);
            request.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
        }
        return SHOW_ALL_BOOKS;
    }
}
