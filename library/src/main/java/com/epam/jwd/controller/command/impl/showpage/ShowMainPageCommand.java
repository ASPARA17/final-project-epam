package com.epam.jwd.controller.command.impl.showpage;

import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;

public class ShowMainPageCommand implements Command {
    private static final Command instance = new ShowMainPageCommand();
    private static final String MAIN_PAGE_PATH = "WEB-INF/jsp/main.jsp";

    private ShowMainPageCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_MAIN_PAGE = new CommandResponse() {
        @Override
        public String getPath() {
            return MAIN_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return SHOW_MAIN_PAGE;
    }
}
