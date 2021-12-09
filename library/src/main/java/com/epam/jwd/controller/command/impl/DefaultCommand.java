package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;

// TODO: add parameters
public class DefaultCommand implements Command {
    private static final Command instance = new DefaultCommand();
    private static final String DEFAULT_PAGE_PATH = "WEB-INF/jsp/main.jsp";

//    private static final String LANGUAGE_ATTRIBUTE = "language";
//    private static final String ENGLISH_LANGUAGE_ATTRIBUTE = "en";

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
//        request.invalidateCurrentSession();
//        HttpSession session = request.createSession();
//        session.setAttribute(LANGUAGE_ATTRIBUTE, ENGLISH_LANGUAGE_ATTRIBUTE);
        return SHOW_DEFAULT_PAGE;
    }
}
