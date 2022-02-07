package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;

public class LogoutCommand implements Command {
    private static final Command instance = new LogoutCommand();
    private static final String MAIN_PAGE = "/library?command=SHOW_MAIN_PAGE";

    private LogoutCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse LOGOUT_SUCCESS_RESPONSE = new CommandResponse
            () {
        @Override
        public String getPath() { return MAIN_PAGE;}

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private static final CommandResponse LOGOUT_ERROR_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.LOGIN_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
//        HttpSession session;
//        if (request.getCurrentSession().isPresent()) {
//            session = request.getCurrentSession().get();
//        } else {
//            return LOGOUT_ERROR_RESPONSE;
//        }

        request.invalidateCurrentSession();
        return LOGOUT_SUCCESS_RESPONSE;
    }
}
