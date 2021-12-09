package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.RequestParameterName;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;

import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    private static final String EN_LANGUAGE = "en";
    private static final String RU_LANGUAGE = "ru";
    public static final String CURRENT_PAGE = "currentPage";

    private static final Command instance = new ChangeLanguageCommand();
    private static String pagePath;

    private ChangeLanguageCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse CHANGE_LANGUAGE_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return pagePath;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private static final CommandResponse SERVER_ERROR_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.ERROR_500;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        String givenLanguage = request.getParameter(RequestParameterName.LANGUAGE);

        if (request.getCurrentSession().isPresent()) {
            HttpSession session = request.getCurrentSession().get();
            session.setAttribute(RequestParameterName.LANGUAGE, givenLanguage);
            pagePath = request.getHeader();
        } else {
            return SERVER_ERROR_RESPONSE;
        }

        return CHANGE_LANGUAGE_RESPONSE;
    }

}

