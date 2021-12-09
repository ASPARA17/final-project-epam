package com.epam.jwd.controller.command.impl.showpage;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;

public class ShowAboutLibraryCommand implements Command {
    private static final Command instance = new ShowAboutLibraryCommand();

    private ShowAboutLibraryCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_ABOUT_LIBRARY = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.ABOUT_LIBRARY_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return SHOW_ABOUT_LIBRARY;
    }
}
