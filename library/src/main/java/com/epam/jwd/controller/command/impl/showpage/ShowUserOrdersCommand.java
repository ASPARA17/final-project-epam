package com.epam.jwd.controller.command.impl.showpage;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.service.dto.orderdto.OrderDto;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.epam.jwd.controller.command.RequestParameterName.USER_ACCOUNT_SESSION_ATTRIB_NAME;
import static com.epam.jwd.controller.command.RequestParameterName.USER_ORDERS;

public class ShowUserOrdersCommand implements Command {
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private static final Command instance = new ShowUserOrdersCommand();
    private static final Logger log = LogManager.getLogger(ShowUserOrdersCommand.class);

    private static final String PAGE_ATTRIBUTE = "page";
    private static final Integer TOTAL_ORDER_ON_PAGE = 7;
    private static final Integer START_PAGE = 1;

    private ShowUserOrdersCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_ORDERS = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.USER_ORDERS_PAGE;
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
            page = page * TOTAL_ORDER_ON_PAGE + 1;
        }

        AccountDto currentAccount =
                (AccountDto) session.getAttribute(USER_ACCOUNT_SESSION_ATTRIB_NAME);
        Integer accountId = currentAccount.getId();

        try {
            List<OrderDto> userOrders = orderService.findOrdersToPageByAccountId(page,
                    TOTAL_ORDER_ON_PAGE, accountId);
            session.setAttribute(USER_ORDERS, userOrders);
        } catch (ServiceException e) {
            log.error(e);
            return ERROR_PAGE;
        }
        return SHOW_ORDERS;
    }
}
