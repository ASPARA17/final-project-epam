package com.epam.jwd.service.impl;

import com.epam.jwd.dao.api.AccountDao;
import com.epam.jwd.dao.entity.user.Account;
import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.dao.impl.AccountDaoImpl;
import com.epam.jwd.service.api.AccountService;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.converter.impl.AccountConverter;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.user.AccountValidator;
import com.epam.jwd.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.jwd.service.exception.ExceptionMessage.*;

public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final Converter<Account, AccountDto, Integer> converter;
    private final Validator<AccountDto, Integer> validator;
    private static AccountServiceImpl instance = new AccountServiceImpl();

    private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);

    private AccountServiceImpl() {
        this.accountDao = AccountDaoImpl.getInstance();
        this.converter = new AccountConverter();
        this.validator = new AccountValidator();
    }

    public static AccountServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<AccountDto> findByUserId(Integer userId) throws ServiceException {
        Optional<AccountDto> accountDtoOptional = Optional.empty();
        try {
            Optional<Account> account = accountDao.findAccountByUserId(userId);
            if (account.isPresent()) {
                AccountDto accountDto = converter.convert(account.get());
                accountDtoOptional = Optional.of(accountDto);
            }
        } catch (DaoException e) {
            log.error(SERVICE_FIND_BY_ID_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_FIND_BY_ID_METHOD_EXCEPTION, e);
        }
        return accountDtoOptional;
    }

    @Override
    public AccountDto create(AccountDto accountDto) throws ServiceException {
        validator.validate(accountDto);
        Account createdAccount = converter.convert(accountDto);
        try {
            accountDto = converter.convert(accountDao.add(createdAccount));
        } catch (DaoException e) {
            log.error(SERVICE_CREATE_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_CREATE_METHOD_EXCEPTION, e);
        }
        return accountDto;
    }

    @Override
    public List<AccountDto> findAll() throws ServiceException {
        List<AccountDto> accountDto = new ArrayList<>();
        try {
            for (Account account : accountDao.findAll()) {
                accountDto.add(converter.convert(account));
            }
        } catch (DaoException e) {
            log.error(SERVICE_FIND_ALL_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_FIND_ALL_METHOD_EXCEPTION, e);
        }
        return accountDto;
    }

    @Override
    public Optional<AccountDto> findById(Integer id) throws ServiceException {
        Optional<AccountDto> accountDtoOptional = Optional.empty();
        try {
            Optional<Account> account = accountDao.findById(id);
            if (account.isPresent()) {
                AccountDto accountDto = converter.convert(account.get());
                accountDtoOptional = Optional.of(accountDto);
            }
        } catch (DaoException e) {
            log.error(SERVICE_FIND_BY_ID_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_FIND_BY_ID_METHOD_EXCEPTION, e);
        }
        return accountDtoOptional;
    }

    @Override
    public List<AccountDto> findUsersOnPage(int page, int totalUsersOnPage) throws ServiceException {
        List<AccountDto> accountDto = new ArrayList<>();
        try {
            for (Account account : accountDao.findAccountsToPage(page, totalUsersOnPage)) {
                accountDto.add(converter.convert(account));
            }
        } catch (DaoException e) {
            log.error(SERVICE_FIND_ALL_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_FIND_ALL_METHOD_EXCEPTION, e);
        }
        return accountDto;
    }

    @Override
    public void editAccount(String firstName, String secondName, String phone,
                            String subscriptionId, Integer accountId) throws ServiceException {
        AccountDto account = new AccountDto.AccountDtoBuilder()
                .withFirstName(firstName)
                .withSecondName(secondName)
                .withPhone(phone)
                .withSubscriptionId(subscriptionId)
                .build();
        validator.validate(account);
        try {
            accountDao.updateAccount(firstName, secondName, phone, subscriptionId, accountId);
        } catch (DaoException e) {
            log.error(SERVICE_UPDATE_METHOD_EXCEPTION, e);
            throw new ServiceException(SERVICE_UPDATE_METHOD_EXCEPTION, e);
        }
    }

}
