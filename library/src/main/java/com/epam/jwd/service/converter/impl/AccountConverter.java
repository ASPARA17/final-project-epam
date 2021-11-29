package com.epam.jwd.service.converter.impl;

import com.epam.jwd.dao.entity.user.Account;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.dto.userdto.AccountDto;

public class AccountConverter implements Converter<Account, AccountDto, Integer> {
    @Override
    public Account convert(AccountDto accountDto) {
        return new Account.AccountBuilder()
                .withId(accountDto.getId())
                .withUserId(accountDto.getUserId())
                .withFirstName(accountDto.getFirstName())
                .withSecondName(accountDto.getSecondName())
                .withPhone(accountDto.getPhone())
                .withSubscriptionId(accountDto.getSubscriptionId())
                .build();
    }

    @Override
    public AccountDto convert(Account account) {
        return new AccountDto.AccountDtoBuilder()
                .withId(account.getId())
                .withUserId(account.getUserId())
                .withFirstName(account.getFirstName())
                .withSecondName(account.getSecondName())
                .withPhone(account.getPhone())
                .withSubscriptionId(account.getSubscriptionId())
                .build();
    }
}
