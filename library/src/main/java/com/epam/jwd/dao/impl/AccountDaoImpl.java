package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.AccountDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.user.Account;
import com.epam.jwd.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.jwd.dao.exception.ExceptionMessage.*;

public class AccountDaoImpl implements AccountDao {
    private static AccountDaoImpl instance = new AccountDaoImpl();
    private ConnectionPool pool;
    private static final Logger log = LogManager.getLogger(AccountDaoImpl.class);

    private AccountDaoImpl() {
        this.pool = ConnectionPoolImpl.getInstance();
    }

    public static AccountDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Account add(Account account) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_ACCOUNT,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, account.getUserId());
            statement.setString(2, account.getFirstName());
            statement.setString(3, account.getSecondName());
            statement.setString(4, account.getPhone());
            statement.setString(5, account.getSubscriptionId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            log.error(CREATE_EXCEPTION, e);
            throw new DaoException(CREATE_EXCEPTION, e);
        }
        return account;
    }

    @Override
    public boolean delete(Account entity) throws DaoException {
        return false;
    }

    @Override
    public List<Account> findAll() throws DaoException {
        List<Account> allAccounts = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ACCOUNTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allAccounts.add(createAccount(resultSet));
            }
        } catch (SQLException e) {
            log.error(FIND_ALL_EXCEPTION, e);
            throw new DaoException(FIND_ALL_EXCEPTION, e);
        }
        return allAccounts;
    }

    @Override
    public Optional<Account> findById(Integer id) throws DaoException {
        Optional<Account> account = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ACCOUNT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = Optional.of(createAccount(resultSet));
            }
        } catch (SQLException e) {
            log.error(FIND_BY_ID_EXCEPTION, e);
            throw new DaoException(FIND_BY_ID_EXCEPTION, e);
        }
        return account;
    }

    private Account createAccount(ResultSet resultSet) throws SQLException{
        return new Account.AccountBuilder()
                .withId(resultSet.getInt(1))
                .withUserId(resultSet.getInt(2))
                .withFirstName(resultSet.getString(3))
                .withSecondName(resultSet.getString(4))
                .withPhone(resultSet.getString(5))
                .withSubscriptionId(resultSet.getString(6))
                .build();
    }

    @Override
    public Optional<Account> findAccountByUserId(Integer userId) throws DaoException {
        Optional<Account> account = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ACCOUNT_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = Optional.of(createAccount(resultSet));
            }
        } catch (SQLException e) {
            log.error(FIND_BY_ID_EXCEPTION, e);
            throw new DaoException(FIND_BY_ID_EXCEPTION, e);
        }
        return account;
    }

    @Override
    public void updatePhoneById(String phone, Integer id) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PHONE_BY_ID)) {
            statement.setString(1, phone);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(UPDATE_EXCEPTION, e);
            throw new DaoException(UPDATE_EXCEPTION, e);
        }
    }

    @Override
    public void updateSubscriptionIdByAccountId(Integer subscriptionId, Integer accountId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_SUBSCRIPTION_ID_BY_ID)) {
            statement.setInt(1, subscriptionId);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(UPDATE_EXCEPTION, e);
            throw new DaoException(UPDATE_EXCEPTION, e);
        }
    }

    @Override
    public List<Account> findAccountsToPage(int page, int totalUsersOnPage) throws DaoException{
        List<Account> allAccountsToPage = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ACCOUNTS_ON_PAGE)) {
            statement.setInt(1, page - 1);
            statement.setInt(2, totalUsersOnPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allAccountsToPage.add(createAccount(resultSet));
            }
        } catch (SQLException e) {
            log.error(FIND_ALL_EXCEPTION, e);
            throw new DaoException(FIND_ALL_EXCEPTION, e);
        }
        return allAccountsToPage;
    }

    @Override
    public void updateAccount(String firstName, String secondName, String phone,
                              String subscriptionId, Integer accountId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ACCOUNT)) {
            statement.setString(1, firstName);
            statement.setString(2, secondName);
            statement.setString(3, phone);
            statement.setString(4, subscriptionId);
            statement.setInt(5, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(UPDATE_EXCEPTION, e);
            throw new DaoException(UPDATE_EXCEPTION, e);
        }

    }
}
