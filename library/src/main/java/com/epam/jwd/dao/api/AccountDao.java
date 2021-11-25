package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.user.Account;
import com.epam.jwd.dao.exception.DaoException;

import java.util.Optional;

public interface AccountDao extends BaseDao<Account, Integer> {
    Optional<Account> findAccountByUserId(Integer userId) throws DaoException;
    void updatePhoneById(String phone, Integer id) throws DaoException;
}
