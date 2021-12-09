package com.epam.jwd.controller.command.impl.showpage;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;

public class ShowRegistrationPageCommand implements Command {
    private static final Command instance = new ShowRegistrationPageCommand();

    private ShowRegistrationPageCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse REGISTRATION_PAGE_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.REGISTRATION_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return REGISTRATION_PAGE_RESPONSE;
    }
}
