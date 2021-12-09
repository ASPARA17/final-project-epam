package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.RequestParameterName;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.dao.entity.user.UserRole;
import com.epam.jwd.service.api.AccountService;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.IncorrectRegisterParametersException;
import com.epam.jwd.service.exception.LoginNotUniqueException;
import com.epam.jwd.service.exception.PasswordNotConfirmedException;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.AccountServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;

public class RegistrationCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final AccountService accountService = AccountServiceImpl.getInstance();
    private static final Command instance = new RegistrationCommand();

    private RegistrationCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse REGISTRATION_SUCCESS_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.LOGIN_PAGE_PATH;
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
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final String confirmPassword = request.getParameter("confirmPassword");
        final String firstName = request.getParameter("firstName");
        final String secondName = request.getParameter("secondName");
        final String phone = request.getParameter("phone");

        try {
            UserDto registerUser = new UserDto(login, password);
            registerUser.setRole(UserRole.USER);
            //userService.verifyPassword(password, confirmPassword);
            UserDto user = userService.create(registerUser);
            System.out.println(user);
            AccountDto registerAccount = new AccountDto(user.getId(),firstName, secondName, phone
                    , null);
            System.out.println(registerAccount);
            accountService.create(registerAccount);

        } catch (ServiceException e) {
            e.printStackTrace();
            return SERVER_ERROR_RESPONSE;
        } catch (LoginNotUniqueException e) {
            request.setAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            //return SERVER_ERROR_RESPONSE;
            return REGISTRATION_ERROR_RESPONSE;
        }
        return REGISTRATION_SUCCESS_RESPONSE;
    }

    private AccountDto creteAccount(Integer userId, String firstName, String secondName,
                                    String phone) {
        return new AccountDto.AccountDtoBuilder()
                        .withUserId(userId)
                        .withFirstName(firstName)
                        .withSecondName(secondName)
                        .withPhone(phone)
                        .build();

    }
}
