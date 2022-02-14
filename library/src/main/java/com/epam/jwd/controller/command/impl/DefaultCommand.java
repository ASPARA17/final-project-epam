package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;

public class DefaultCommand implements Command {
    private static final Command instance = new DefaultCommand();
    private static final String DEFAULT_PAGE_PATH = "WEB-INF/jsp/main.jsp";


    private DefaultCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_DEFAULT_PAGE = new CommandResponse() {
        @Override
        public String getPath() {
            return DEFAULT_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return SHOW_DEFAULT_PAGE;
    }
}
