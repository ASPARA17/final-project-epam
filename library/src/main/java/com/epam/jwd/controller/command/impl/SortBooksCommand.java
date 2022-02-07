package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.service.api.BookService;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.impl.BookServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.epam.jwd.controller.command.RequestParameterName.ALL_BOOKS;
import static com.epam.jwd.controller.command.RequestParameterName.BOOK_SORT_PARAM;

public class SortBooksCommand implements Command {
    private final BookService bookService = BookServiceImpl.getInstance();
    private static final Command instance = new SortBooksCommand();

    private SortBooksCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_SORTED_BOOKS = new CommandResponse() {
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

        String sortParam = request.getParameter(BOOK_SORT_PARAM);
        List<BookDto> allBooks;

        allBooks = (List<BookDto>) session.getAttribute(ALL_BOOKS);

        List<BookDto> sortedBooks = bookService.sortByParameter(allBooks, sortParam);
        session.setAttribute(ALL_BOOKS, sortedBooks);

        return SHOW_SORTED_BOOKS;
    }
}
