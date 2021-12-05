package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.CommandRequest;
import com.epam.jwd.controller.command.CommandResponse;
import com.epam.jwd.service.api.AccountService;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.IncorrectRegisterParametersException;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.AccountServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();
    private final AccountService accountService = AccountServiceImpl.getInstance();
    private static final Command instance = new LoginCommand();
    private static final String LOGIN_PAGE_PATH = "WEB-INF/jsp/login.jsp";
    private static final String USER_HOME_PAGE_PATH = "WEB-INF/jsp/";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ERROR_ATTRIB_NAME = "error";
    private static final String INVALID_CREDENTIALS_MSG = "Wrong login or password";
    private static final String USER_NAME_SESSION_ATTRIB_NAME = "userName";
    public static final String USER_ROLE_SESSION_ATTRIB_NAME = "userRole";
    private static final String SPACE = " ";



    private LoginCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse LOGIN_SUCCESS_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return USER_HOME_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private static final CommandResponse LOGIN_ERROR_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return LOGIN_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };




    @Override
    public CommandResponse execute(CommandRequest request) {
        final String login = request.getParameter(LOGIN);
        final String password = request.getParameter(PASSWORD);
        try {
            Optional<UserDto> currentUser = userService.signInUser(login, password);
            if (currentUser.isPresent()) {
                UserDto user = currentUser.get();
                Optional<AccountDto> currentAccount = accountService.findByUserId(user.getId());
                if (currentAccount.isPresent()) {
                    AccountDto account = currentAccount.get();
                    addUserInfoToSession(request, user, account);
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IncorrectRegisterParametersException e) {
            return prepareErrorPage(request);
        }


        return null;
    }

    private CommandResponse addUserInfoToSession(CommandRequest request, UserDto user,
                                                 AccountDto account) {
        request.getCurrentSession().ifPresent(HttpSession::invalidate);
        final HttpSession session = request.createSession();
        session.setAttribute(USER_NAME_SESSION_ATTRIB_NAME, user.getRole());
        session.setAttribute(USER_ROLE_SESSION_ATTRIB_NAME, account);
        session.setAttribute("", account.getFirstName() + SPACE + account.getSecondName());
        return LOGIN_SUCCESS_RESPONSE;
    }

    private CommandResponse prepareErrorPage(CommandRequest request) {
        request.setAttribute(ERROR_ATTRIB_NAME, INVALID_CREDENTIALS_MSG);
        return LOGIN_ERROR_RESPONSE;
    }
}
