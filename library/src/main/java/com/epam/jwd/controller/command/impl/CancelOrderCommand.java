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
import static com.epam.jwd.controller.command.RequestParameterName.ORDER_NAME_BOOK;

public class CancelOrderCommand implements Command {
    private static final Logger log = LogManager.getLogger(CancelOrderCommand.class);
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private final BookServiceImpl bookService = BookServiceImpl.getInstance();
    private static final String ORDERS_PAGE = "/library?command=SHOW_USER_ORDERS";
    private static final Command instance = new CancelOrderCommand();
    private static final String CANCEL_SUCCESS = "successCancel";

    private CancelOrderCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_ORDERS = new CommandResponse() {
        @Override
        public String getPath() {
            return ORDERS_PAGE;
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
        boolean isCancelSuccessful;
        String orderId = request.getParameter(ORDER_ID);

        try {
            Optional<OrderDto> foundOrder = orderService.findById(Integer.parseInt(orderId));
            if (foundOrder.isPresent()) {
                OrderDto canceledOrder = foundOrder.get();

                Integer bookId = canceledOrder.getBook().getId();
                BookDto book = findBook(bookId);
                if (book == null) {
                    return ERROR_PAGE;
                }
                bookService.updateQuantityById(book.getQuantity() + 1, bookId);
                orderService.cancelOrder(Integer.parseInt(orderId));

                isCancelSuccessful = true;
                String nameOfBook = book.getName();
                //todo for notification
                session.setAttribute(CANCEL_SUCCESS, isCancelSuccessful);
                session.setAttribute(ORDER_ID, orderId);
                session.setAttribute(ORDER_NAME_BOOK, nameOfBook);
            } else {
                log.error("");
                return ERROR_PAGE;
            }
        } catch (ServiceException e) {
            log.error(e);
            return ERROR_PAGE;
        }
        return SHOW_ORDERS;
    }

    private BookDto findBook(Integer bookId) throws ServiceException {
        BookDto book = null;
        Optional<BookDto> foundBook = bookService.findById(bookId);
        if (foundBook.isPresent()) {
            book = foundBook.get();
        } else {
            log.error("");
        }
        return book;
    }
}
