package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.RequestParameterName;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.service.api.AccountService;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.IncorrectSignInParametersException;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.AccountServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final AccountService accountService = AccountServiceImpl.getInstance();
    private static final Command instance = new LoginCommand();

    private static final String ERROR_ATTRIB_NAME = "wrongData";
    private static final String INVALID_CREDENTIALS_MSG = "Wrong login or password";
    private static final String USER_ACCOUNT_SESSION_ATTRIB_NAME = "account";
    private static final String SPACE = " ";

    public static final String USER_ROLE_SESSION_ATTRIB_NAME = "userRole";

    private LoginCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse LOGIN_SUCCESS_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() { return "/library?command=SHOW_MAIN_PAGE";}

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private static final CommandResponse LOGIN_ERROR_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.LOGIN_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final CommandResponse SERVER_ERROR_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.ERROR_404;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String login = request.getParameter(RequestParameterName.LOGIN);
        final String password = request.getParameter(RequestParameterName.PASSWORD);
        try {
            Optional<UserDto> currentUser = userService.signInUser(login, password);
            if (currentUser.isPresent()) {
                UserDto user = currentUser.get();
                Optional<AccountDto> currentAccount = accountService.findByUserId(user.getId());
                if (currentAccount.isPresent()) {
                    AccountDto account = currentAccount.get();
                    return addUserInfoToSession(request, user, account);
                }
            }
        } catch (ServiceException e) {
            return SERVER_ERROR_RESPONSE;
        } catch (IncorrectSignInParametersException e) {
            return prepareErrorPage(request);
        }
        return LOGIN_ERROR_RESPONSE;
    }

        private CommandResponse addUserInfoToSession(CommandRequest request, UserDto user,
                                                     AccountDto account) {
            request.getCurrentSession().ifPresent(HttpSession::invalidate);
            final HttpSession session = request.createSession();
            session.setAttribute("user", user);
            session.setAttribute(USER_ROLE_SESSION_ATTRIB_NAME, user.getRole());
            session.setAttribute(USER_ACCOUNT_SESSION_ATTRIB_NAME, account);
            session.setAttribute("fullName",
                    account.getFirstName() + SPACE + account.getSecondName());
            return LOGIN_SUCCESS_RESPONSE;
        }

    private CommandResponse prepareErrorPage(CommandRequest request) {
        request.setAttribute(ERROR_ATTRIB_NAME, INVALID_CREDENTIALS_MSG);
        return LOGIN_ERROR_RESPONSE;
    }
}
