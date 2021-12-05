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
import com.epam.jwd.service.validator.AccountValidator;
import com.epam.jwd.service.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final Converter<Account, AccountDto, Integer> converter;
    private final Validator<AccountDto, Integer> validator;
    private static volatile AccountServiceImpl instance;

    private AccountServiceImpl() {
        this.accountDao = AccountDaoImpl.getInstance();
        this.converter = new AccountConverter();
        this.validator = new AccountValidator();
    }

    public static AccountServiceImpl getInstance() {
        AccountServiceImpl localInstance = instance;
        if (instance == null) {
            synchronized (AccountServiceImpl.class) {
                localInstance = instance;
                if (instance == null) {
                    instance = localInstance = new AccountServiceImpl();
                }
            }
        }
        return  localInstance;
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
            throw new ServiceException();
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
            throw new ServiceException();
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
            throw new ServiceException();
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
            throw new ServiceException();
        }
        return accountDtoOptional;
    }
}
