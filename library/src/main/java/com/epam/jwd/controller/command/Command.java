package com.epam.jwd.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandResponse execute(CommandRequest request);

    static Command of(String commandName) {
        return Commands.of(commandName);
    }

}
