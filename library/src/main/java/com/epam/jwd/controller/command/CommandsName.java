package com.epam.jwd.controller.command;

import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.impl.*;
import com.epam.jwd.controller.command.impl.showpage.*;
import com.epam.jwd.dao.entity.user.UserRole;

import java.util.Arrays;
import java.util.List;

import static com.epam.jwd.dao.entity.user.UserRole.*;

public enum CommandsName {
    LOGIN(LoginCommand.getInstance(), GUEST),
    LOGOUT(LogoutCommand.getInstance(), ADMIN, USER),
    SHOW_LOGIN_PAGE(ShowLoginPageCommand.getInstance(), GUEST),
    SHOW_MAIN_PAGE(ShowMainPageCommand.getInstance()),
    SHOW_ALL_BOOKS(ShowCatalogCommand.getInstance(), USER, ADMIN),
    CHANGE_LANGUAGE(ChangeLanguageCommand.getInstance()),
    SHOW_ERROR_PAGE(ShowErrorPageCommand.getInstance()),
    REGISTRATION(RegistrationCommand.getInstance(), GUEST),
    SHOW_REGISTRATION_PAGE(ShowRegistrationPageCommand.getInstance(), GUEST),
    MAKE_ORDER(MakeOrderCommand.getInstance(), USER),
    SORT_BOOKS(SortBooksCommand.getInstance(), USER, ADMIN),
    FILTER_BOOKS(FilterBooksCommand.getInstance(), USER, ADMIN),
    SHOW_USER_ORDERS(ShowUserOrdersCommand.getInstance(), USER),
    CANCEL_ORDER(CancelOrderCommand.getInstance(), USER, ADMIN),
    SHOW_ALL_USERS(ShowAllUsersCommand.getInstance(), ADMIN),
    SHOW_ADD_BOOK_PAGE(ShowAddBookPageCommand.getInstance(), ADMIN),
    SHOW_ALL_ORDERS(ShowAllOrdersCommand.getInstance(), ADMIN),
    ADD_BOOK(AddBookCommand.getInstance(), ADMIN),
    RETURN_BOOK(ReturnBookCommand.getInstance(), ADMIN),
    CONFIRM_ORDER(ConfirmOrderCommand.getInstance(), ADMIN),
    SHOW_EDIT_BOOK_PAGE(ShowEditBookPageCommand.getInstance(), ADMIN),
    EDIT_BOOK(EditBookCommand.getInstance(), ADMIN),
    SHOW_EDIT_ACCOUNT_PAGE(ShowEditAccountPageCommand.getInstance(), USER),
    EDIT_ACCOUNT(EditAccountCommand.getInstance(), USER),
    DEFAULT(DefaultCommand.getInstance());


    private final Command command;
    private final List<UserRole> allowedRoles;

    CommandsName(Command command, UserRole... roles) {
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

    public static CommandsName of(String commandName) {
        for (CommandsName command : values()) {
            if (command.name().equalsIgnoreCase(commandName)) {
                return command;
            }
        }
        return DEFAULT;
    }



}
