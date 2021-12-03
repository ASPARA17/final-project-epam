package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.CommandRequest;
import com.epam.jwd.controller.command.CommandResponse;
import com.epam.jwd.service.impl.UserServiceImpl;

public class LoginCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final Command instance = new LoginCommand();
    private static final String LOGIN_PAGE_PATH = "WEB-INF/jsp/login.jsp";
    private static final String USER_HOME_PAGE_PATH = "WEB-INF/jsp/";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";



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

        return null;
    }
}
