package com.epam.jwd.controller.command;

import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.impl.ChangeLanguageCommand;
import com.epam.jwd.controller.command.impl.DefaultCommand;
import com.epam.jwd.controller.command.impl.LoginCommand;
import com.epam.jwd.controller.command.impl.RegistrationCommand;
import com.epam.jwd.controller.command.impl.showpage.*;
import com.epam.jwd.dao.entity.user.UserRole;

import java.util.Arrays;
import java.util.List;

public enum NameCommands {
    LOGIN(LoginCommand.getInstance()),
    SHOW_LOGIN_PAGE(ShowLoginPageCommand.getInstance()),
    SHOW_MAIN_PAGE(ShowMainPageCommand.getInstance()),
    SHOW_ABOUT_LIBRARY(ShowAboutLibraryCommand.getInstance()),
    SHOW_ALL_BOOKS(ShowAllBooksCommand.getInstance()),
    CHANGE_LANGUAGE(ChangeLanguageCommand.getInstance()),
    SHOW_ERROR_PAGE(ShowErrorPageCommand.getInstance()),
    REGISTRATION(RegistrationCommand.getInstance()),
    SHOW_REGISTRATION_PAGE(ShowRegistrationPageCommand.getInstance()),
    DEFAULT(DefaultCommand.getInstance());


    private final Command command;
    private final List<UserRole> allowedRoles;

    NameCommands(Command command, UserRole... roles) {
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

    public static NameCommands of(String commandName) {
        for (NameCommands command : values()) {
            if (command.name().equalsIgnoreCase(commandName)) {
                return command;
            }
        }
        return DEFAULT;
    }



}
