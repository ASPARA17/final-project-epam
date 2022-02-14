package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.dao.entity.book.Genre;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.BookServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static com.epam.jwd.controller.command.RequestParameterName.*;

public class AddBookCommand implements Command {
    private static final Command instance = new AddBookCommand();
    private static final Logger log = LogManager.getLogger(AddBookCommand.class);
    private static final String ADD_BOOK_SUCCESS = "successAddBook";
    private static final String ADD_BOOK_PAGE = "/library?command=SHOW_ADD_BOOK_PAGE";
    private final BookServiceImpl bookService = BookServiceImpl.getInstance();
    private static final String ERROR_MESSAGE = "Can't add book";
    private static final String ERROR_ATTRIBUTE = "error";

    private AddBookCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_ADD_BOOK_PAGE= new CommandResponse() {
        @Override
        public String getPath() {
            return ADD_BOOK_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private static final CommandResponse FAIL_ADD_PAGE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.ADD_BOOK_PAGE;
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

        boolean isAddBookSuccessful;
        String author = request.getParameter(BOOK_AUTHOR);
        String name = request.getParameter(BOOK_NAME);
        String publisher = request.getParameter(BOOK_PUBLISHER);
        int genreId = Integer.parseInt(request.getParameter(BOOK_GENRE));
        Genre genre = Genre.getGenreById(genreId);
        int yearPublishing = Integer.parseInt(request.getParameter(BOOK_YEAR));
        int pages = Integer.parseInt(request.getParameter(BOOK_PAGES));
        int quantity = Integer.parseInt(request.getParameter(BOOK_QUANTITY));

        BookDto book = createBook(author, name, publisher, genre, yearPublishing, pages, quantity);

        try {
            bookService.create(book);
            isAddBookSuccessful = true;
            session.setAttribute(ADD_BOOK_SUCCESS, isAddBookSuccessful);
        } catch (ServiceException e) {
            log.error(ERROR_MESSAGE, e);
            request.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
            return FAIL_ADD_PAGE;
        }
        return SHOW_ADD_BOOK_PAGE;
    }

    private BookDto createBook(String author, String name, String publisher, Genre genre, int year,
                               int pages, int quantity) {
        return new BookDto.BookDtoBuilder()
                .withAuthor(author)
                .withName(name)
                .withPublishingHouse(publisher)
                .withGenre(genre)
                .withYearPublishing(year)
                .withNumberOfPage(pages)
                .withQuantity(quantity)
                .build();
    }
}
