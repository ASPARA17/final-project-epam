package com.epam.jwd.controller.command.impl.showpage;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowAddBookPageCommand implements Command {
    private static final Command instance = new ShowAddBookPageCommand();
    private static final Logger log = LogManager.getLogger(ShowAddBookPageCommand.class);

    private ShowAddBookPageCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse ADD_BOOKS_PAGE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.ADD_BOOK_PAGE   ;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return ADD_BOOKS_PAGE;
    }
}
