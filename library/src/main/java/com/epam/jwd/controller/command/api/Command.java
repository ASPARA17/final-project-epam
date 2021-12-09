package com.epam.jwd.controller.command.api;

import com.epam.jwd.controller.command.NameCommands;

public interface Command {
    CommandResponse execute(CommandRequest request);

    static Command withName(String name) {
        return NameCommands.of(name)
                .getCommand();
    }
}
