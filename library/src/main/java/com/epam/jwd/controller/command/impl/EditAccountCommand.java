package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.PagePath;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.AccountServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.epam.jwd.controller.command.RequestParameterName.*;

public class EditAccountCommand implements Command {
    private static final Command instance = new EditAccountCommand();
    private static final Logger log = LogManager.getLogger(EditAccountCommand.class);
    private static final AccountServiceImpl accountService = AccountServiceImpl.getInstance();
    private static final String EDIT_ACCOUNT_SUCCESS = "successEditAccount";
    private static final String EDIT_PAGE = "/library?command=SHOW_EDIT_ACCOUNT_PAGE";
    private static final String ERROR_MESSAGE = "Can't edit account";
    private static final String ERROR_ATTRIBUTE = "error";


    private EditAccountCommand() {
    }

    public static Command getInstance() {
        return instance;
    }

    private static final CommandResponse SHOW_EDIT_PAGE = new CommandResponse() {
        @Override
        public String getPath() {
            return EDIT_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private static final CommandResponse ERROR_PAGE = new CommandResponse() {
        @Override
        public String getPath() {
            return PagePath.ERROR_404;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        HttpSession session;
        if (request.getCurrentSession().isPresent()) {
            session = request.getCurrentSession().get();
        } else {
            return ERROR_PAGE;
        }
        boolean isEditAccountSuccessful;
        String firstName = request.getParameter(ACCOUNT_NAME);
        String secondName = request.getParameter(ACCOUNT_SECOND_NAME);
        String phone = request.getParameter(ACCOUNT_PHONE);
        String subscriptionId = request.getParameter(ACCOUNT_SUBSCRIPTION);
        AccountDto currentAccount =
                (AccountDto) session.getAttribute(USER_ACCOUNT_SESSION_ATTRIB_NAME);
        AccountDto editAccount = createAccount(firstName, secondName, phone, subscriptionId);
        try {
            accountService.editAccount(editAccount, currentAccount.getId());
            isEditAccountSuccessful = true;
            session.setAttribute(EDIT_ACCOUNT_SUCCESS, isEditAccountSuccessful);
            Optional<AccountDto> foundAccount = accountService.findById(currentAccount.getId());
            if (foundAccount.isPresent()) {
                AccountDto updateAccount = foundAccount.get();
                session.setAttribute(USER_ACCOUNT_SESSION_ATTRIB_NAME, updateAccount);
            } else {
                log.error(ERROR_MESSAGE);
                request.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
                return SHOW_EDIT_PAGE;
            }
        } catch (ServiceException e) {
            log.error(ERROR_MESSAGE, e);
            request.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
            return SHOW_EDIT_PAGE;
        }
        return SHOW_EDIT_PAGE;
    }

    private AccountDto createAccount(String firstName, String secondName, String phone,
                            String subscriptionId) {
        return new AccountDto.AccountDtoBuilder()
                .withFirstName(firstName)
                .withSecondName(secondName)
                .withPhone(phone)
                .withSubscriptionId(subscriptionId)
                .build();
    }
}
