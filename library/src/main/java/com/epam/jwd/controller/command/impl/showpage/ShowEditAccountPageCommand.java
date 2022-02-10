package com.epam.jwd.controller.command.impl.showpage;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static com.epam.jwd.controller.command.RequestParameterName.*;

public class ShowEditAccountPageCommand implements Command {
    private static final Command instance = new ShowEditAccountPageCommand();
    private static final Logger log = LogManager.getLogger(ShowEditAccountPageCommand.class);

    private ShowEditAccountPageCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_EDIT_PAGE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.EDIT_ACCOUNT_PAGE;
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

        AccountDto currentAccount =
                (AccountDto) session.getAttribute(USER_ACCOUNT_SESSION_ATTRIB_NAME);

        session.setAttribute(ACCOUNT_NAME, currentAccount.getFirstName());
        session.setAttribute(ACCOUNT_SECOND_NAME, currentAccount.getSecondName());
        session.setAttribute(ACCOUNT_PHONE, currentAccount.getPhone());
        session.setAttribute(ACCOUNT_SUBSCRIPTION, currentAccount.getSubscriptionId());
        return SHOW_EDIT_PAGE;
    }
}
