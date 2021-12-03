package com.epam.jwd.controller.command;

import com.epam.jwd.controller.command.impl.showpage.DefaultCommand;
import com.epam.jwd.controller.command.impl.showpage.ShowMainPageCommand;
import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.dao.entity.user.UserRole;

import java.util.Arrays;
import java.util.List;

public enum Commands {
    MAIN_PAGE(ShowMainPageCommand.getInstance()),
    DEFAULT(DefaultCommand.getInstance());

    private final Command command;
    private final List<UserRole> allowedRoles;

    Commands(Command command, UserRole... roles) {
        this.command = command;
        this.allowedRoles = roles != null
                && roles.length > 0
                ? Arrays.asList(roles)
                : UserRole.valuesAsList();
    }

    public Command getCommand() {
        return command;
    }

    public List<UserRole> getAllowedRoles() {
        return allowedRoles;
    }

    public static Command of(String commandName) {
        return Arrays.stream(Commands.values())
                .filter(command -> command.name().equalsIgnoreCase(commandName))
                .map(command -> command.command)
                .findFirst()
                .orElse(DefaultCommand.getInstance());
    }



}
