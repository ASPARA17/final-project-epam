package com.epam.jwd.controller.command.impl;

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

    @Override
    public CommandResponse execute(CommandRequest request) {
        request.invalidateCurrentSession();
        return LOGOUT_SUCCESS_RESPONSE;
    }
}
