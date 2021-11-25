package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.user.Account;
import com.epam.jwd.dao.exception.DaoException;

public interface AccountDao extends BaseDao<Account, Integer> {
    void createAccount(Account account) throws DaoException;

}
