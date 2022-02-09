package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static com.epam.jwd.controller.command.RequestParameterName.ORDER_ID;

public class ConfirmOrderCommand implements Command {
    private static final Logger log = LogManager.getLogger(ConfirmOrderCommand.class);
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private static final String ALL_ORDERS_PAGE = "/library?command=SHOW_ALL_ORDERS";
    private static final String CONFIRM_ORDER_SUCCESS = "successConfirmOrder";
    private static final Command instance = new ConfirmOrderCommand();

    private ConfirmOrderCommand() {
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

        boolean isConfirmOrderSuccessful;
        Integer orderId = Integer.parseInt(request.getParameter(ORDER_ID));

        try {
            orderService.confirmOrder(orderId);
            isConfirmOrderSuccessful = true;
            session.setAttribute(ORDER_ID, orderId);
            session.setAttribute(CONFIRM_ORDER_SUCCESS, isConfirmOrderSuccessful);
        } catch (ServiceException e) {
            log.error(e);
            return ERROR_PAGE;
        }
        return SHOW_ORDERS_PAGE;
    }
}
