package com.epam.jwd.controller.command.impl.showpage;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;

public class ShowAllBooksCommand implements Command {
    private static final Command instance = new ShowAllBooksCommand();

    private ShowAllBooksCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_ALL_BOOKS = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.CATALOG_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return SHOW_ALL_BOOKS;
    }
}
