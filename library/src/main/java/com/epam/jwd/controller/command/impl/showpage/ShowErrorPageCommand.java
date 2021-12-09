package com.epam.jwd.controller.command.impl.showpage;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;

public class ShowErrorPageCommand implements Command {
    private static final Command instance = new ShowErrorPageCommand();

    private ShowErrorPageCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse ERROR_500_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.ERROR_500;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return ERROR_500_RESPONSE;
    }
}
