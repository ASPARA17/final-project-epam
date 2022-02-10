package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.user.Account;
import com.epam.jwd.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface AccountDao extends BaseDao<Account, Integer> {
    Optional<Account> findAccountByUserId(Integer userId) throws DaoException;
    void updatePhoneById(String phone, Integer id) throws DaoException;
    void updateSubscriptionIdByAccountId(Integer subscriptionId, Integer accountId) throws DaoException;
    List<Account> findAccountsToPage(int page, int totalUsersOnPage) throws DaoException;
    void updateAccount (String firstName, String secondName, String phone,
                        Integer subscriptionId, Integer accountId) throws DaoException;
}
