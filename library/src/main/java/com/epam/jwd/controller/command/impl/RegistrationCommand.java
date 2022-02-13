package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.dao.entity.user.UserRole;
import com.epam.jwd.service.api.AccountService;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.LoginNotUniqueException;
import com.epam.jwd.service.exception.PasswordNotConfirmedException;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.AccountServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;

import static com.epam.jwd.controller.command.RequestParameterName.*;

public class RegistrationCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final AccountService accountService = AccountServiceImpl.getInstance();
    private static final Command instance = new RegistrationCommand();
    private static final String LOGIN_PAGE = "/library?command=SHOW_LOGIN_PAGE";

    private RegistrationCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse REGISTRATION_SUCCESS_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return LOGIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private static final CommandResponse REGISTRATION_ERROR_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.REGISTRATION_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static final CommandResponse SERVER_ERROR_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.ERROR_500;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
        String firstName = request.getParameter(ACCOUNT_NAME);
        String secondName = request.getParameter(ACCOUNT_SECOND_NAME);
        String phone = request.getParameter(ACCOUNT_PHONE);
        String subscriptionId = request.getParameter(ACCOUNT_SUBSCRIPTION);
        boolean isRegistrationSuccessful;

        try {
            UserDto registerUser = new UserDto(login, password);
            registerUser.setRole(UserRole.USER);
            UserDto user = userService.create(registerUser, confirmPassword);
            AccountDto registerAccount = creteAccount(user, firstName, secondName, phone, subscriptionId);
            accountService.create(registerAccount);
            isRegistrationSuccessful = true;

        } catch (ServiceException e) {
            return SERVER_ERROR_RESPONSE;
        } catch (LoginNotUniqueException | PasswordNotConfirmedException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return REGISTRATION_ERROR_RESPONSE;
        }
        return successRegistration(request, isRegistrationSuccessful);
    }

    private CommandResponse successRegistration(CommandRequest request, boolean isSuccessRegister) {
        request.getCurrentSession().ifPresent(HttpSession::invalidate);
        final HttpSession session = request.createSession();
        session.setAttribute("successRegister", isSuccessRegister);
        return REGISTRATION_SUCCESS_RESPONSE;
    }

    private AccountDto creteAccount(UserDto user, String firstName, String secondName,
                                    String phone, String subscriptionId) {
        return new AccountDto.AccountDtoBuilder()
                        .withUser(user)
                        .withFirstName(firstName)
                        .withSecondName(secondName)
                        .withPhone(phone)
                        .withSubscriptionId(subscriptionId)
                        .build();

    }
}
