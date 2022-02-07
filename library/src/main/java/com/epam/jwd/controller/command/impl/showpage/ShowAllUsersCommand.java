package com.epam.jwd.controller.command.impl.showpage;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.AccountServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.epam.jwd.controller.command.RequestParameterName.ALL_USERS;

public class ShowAllUsersCommand implements Command {
    private final AccountServiceImpl accountService = AccountServiceImpl.getInstance();
    private static final Command instance = new ShowAllUsersCommand();
    private static final Logger log = LogManager.getLogger(ShowAllUsersCommand.class);

    private static final String PAGE_ATTRIBUTE = "page";
    private static final Integer TOTAL_ORDER_ON_PAGE = 7;
    private static final Integer START_PAGE = 1;

    private ShowAllUsersCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_USERS = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.ALL_USERS_PAGE;
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

        try {
            List<AccountDto> allUsers = accountService.findUsersOnPage(page, TOTAL_ORDER_ON_PAGE);
            session.setAttribute(ALL_USERS, allUsers);
        } catch (ServiceException e) {
            log.error(e);
            return ERROR_PAGE;
        }
        return SHOW_USERS;
    }
}
