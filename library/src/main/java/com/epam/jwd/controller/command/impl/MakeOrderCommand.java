package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.dto.orderdto.OrderDto;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.BookServiceImpl;
import com.epam.jwd.service.impl.OrderServiceImpl;
import com.epam.jwd.service.validator.date.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.util.Optional;

import static com.epam.jwd.controller.command.RequestParameterName.*;

public class MakeOrderCommand implements Command {
    private static final Logger log = LogManager.getLogger(MakeOrderCommand.class);
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private final BookServiceImpl bookService = BookServiceImpl.getInstance();
    private static final String CATALOG_PAGE = "/library?command=SHOW_ALL_BOOKS";
    private static final Command instance = new MakeOrderCommand();
    private static final String MAKE_ORDER_SUCCESS = "successMakeOrder";
    private static final String ERROR_MESSAGE = "Can't make order";
    private static final String ERROR_ATTRIBUTE = "error";

    private MakeOrderCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_CATALOG = new CommandResponse() {
        @Override
        public String getPath() {
            return CATALOG_PAGE;
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

        boolean isMakeOrderSuccessful;
        AccountDto currentAccount =
                (AccountDto) session.getAttribute(USER_ACCOUNT_SESSION_ATTRIB_NAME);

        java.util.Date dateOfIssueUtil = DateUtil.takeCurrentDateFormat();
        java.sql.Date dateOfIssue = new java.sql.Date(dateOfIssueUtil.getTime());
        String bookId = request.getParameter(BOOK_ID);

        boolean subscription = false;
        if (currentAccount.getSubscriptionId() != null) {
            if(!currentAccount.getSubscriptionId().equals("")) {
                subscription = true;
            }
        }

        try {
            Optional<BookDto> foundBook = bookService.findById(Integer.parseInt(bookId));
            if (foundBook.isPresent()) {
                BookDto orderBook = foundBook.get();

                OrderDto order = createOrder(currentAccount, orderBook, dateOfIssue,
                        subscription);

                orderService.makeOrder(order);
                bookService.updateQuantityById(orderBook.getQuantity() - 1, Integer.parseInt(bookId));

                isMakeOrderSuccessful = true;

                session.setAttribute(ORDER_NAME_BOOK, orderBook.getName());
                session.setAttribute(MAKE_ORDER_SUCCESS, isMakeOrderSuccessful);
            } else {
                log.error(ERROR_MESSAGE);
                request.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
                return SHOW_CATALOG;
            }
        } catch (ServiceException e) {
            log.error(ERROR_MESSAGE, e);
            request.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
            return SHOW_CATALOG;
        }
        return SHOW_CATALOG;
    }

    private OrderDto createOrder(AccountDto account, BookDto book, Date dateOfIssue,
                                 boolean subscription) {
        return new OrderDto.OrderDtoBuilder()
                .withAccount(account)
                .withBook(book)
                .withDateOfIssue(dateOfIssue)
                .withSubscription(subscription)
                .build();
    }
}
