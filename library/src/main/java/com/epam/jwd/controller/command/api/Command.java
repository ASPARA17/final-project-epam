package com.epam.jwd.controller.command.api;

import com.epam.jwd.controller.command.CommandsName;

public interface Command {
    CommandResponse execute(CommandRequest request);

    static Command withName(String name) {
        return CommandsName.of(name)
                .getCommand();
    }
}
