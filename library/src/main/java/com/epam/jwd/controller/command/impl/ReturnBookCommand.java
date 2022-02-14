package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.dto.orderdto.OrderDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.BookServiceImpl;
import com.epam.jwd.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.epam.jwd.controller.command.RequestParameterName.ORDER_ID;

public class ReturnBookCommand implements Command {
    private static final Logger log = LogManager.getLogger(ReturnBookCommand.class);
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private final BookServiceImpl bookService = BookServiceImpl.getInstance();
    private static final String ALL_ORDERS_PAGE = "/library?command=SHOW_ALL_ORDERS";
    private static final Command instance = new ReturnBookCommand();
    private static final String RETURN_BOOK_SUCCESS = "successReturnBook";
    private static final String ERROR_MESSAGE = "Can't return book";
    private static final String ERROR_ATTRIBUTE = "error";

    private ReturnBookCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_ORDERS_PAGE = new CommandResponse() {
        @Override
        public String getPath() {
            return ALL_ORDERS_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return true;
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

        boolean isReturnBookSuccessful;
        Integer orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        try {
            Optional<OrderDto> foundOrder = orderService.findById(orderId);
            if (foundOrder.isPresent()) {
                OrderDto order = foundOrder.get();

                BookDto book = order.getBook();
                orderService.returnBook(orderId);
                bookService.updateQuantityById(book.getQuantity() + 1, book.getId());
                isReturnBookSuccessful = true;

                session.setAttribute(ORDER_ID, orderId);
                session.setAttribute(RETURN_BOOK_SUCCESS, isReturnBookSuccessful);
            } else {
                log.error(ERROR_MESSAGE);
                request.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
            }
        } catch (ServiceException e) {
            log.error(ERROR_MESSAGE, e);
            request.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
            return SHOW_ORDERS_PAGE;
        }
        return SHOW_ORDERS_PAGE;
    }
}
