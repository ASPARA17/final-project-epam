package com.epam.jwd.service.api;

import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ServiceException;

public interface AccountService extends Service<AccountDto, Integer> {
    AccountDto create(AccountDto accountDto) throws ServiceException;
}
