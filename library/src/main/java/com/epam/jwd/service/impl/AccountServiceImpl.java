package com.epam.jwd.service.impl;

import com.epam.jwd.service.api.AccountService;
import com.epam.jwd.service.dto.userdto.AccountDto;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    @Override
    public AccountDto add(AccountDto entity) {
        return null;
    }

    @Override
    public List<AccountDto> findAll() {
        return null;
    }

    @Override
    public Optional<AccountDto> findById(Integer id) {
        return Optional.empty();
    }
}
