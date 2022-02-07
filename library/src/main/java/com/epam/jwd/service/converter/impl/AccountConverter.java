package com.epam.jwd.service.converter.impl;

import com.epam.jwd.dao.api.UserDao;
import com.epam.jwd.dao.entity.user.Account;
import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.dto.userdto.UserDto;

public class AccountConverter implements Converter<Account, AccountDto, Integer> {

    private final UserDao userDao = UserDaoImpl.getInstance();
    private final Converter<User, UserDto, Integer> userConverter = new UserConverter();

    @Override
    public Account convert(AccountDto accountDto) {
        return new Account.AccountBuilder()
                .withId(accountDto.getId())
                .withUserId(accountDto.getUser().getId())
                .withFirstName(accountDto.getFirstName())
                .withSecondName(accountDto.getSecondName())
                .withPhone(accountDto.getPhone())
                .withSubscriptionId(accountDto.getSubscriptionId())
                .build();
    }

    @Override
    public AccountDto convert(Account account) throws DaoException {
        User user = null;
        if (userDao.findById(account.getUserId()).isPresent()) {
            user = userDao.findById(account.getUserId()).get();
        }

        return new AccountDto.AccountDtoBuilder()
                .withId(account.getId())
                .withUser(userConverter.convert(user))
                .withFirstName(account.getFirstName())
                .withSecondName(account.getSecondName())
                .withPhone(account.getPhone())
                .withSubscriptionId(account.getSubscriptionId())
                .build();
    }
}
