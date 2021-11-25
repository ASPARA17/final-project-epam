package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.AccountDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.user.Account;
import com.epam.jwd.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl implements AccountDao {
    private static volatile AccountDaoImpl instance;
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private AccountDaoImpl() {
    }

    public static AccountDaoImpl getInstance() {
        AccountDaoImpl localInstance = instance;
        if (instance == null) {
            synchronized (AccountDaoImpl.class) {
                localInstance = instance;
                if (instance == null) {
                    instance = localInstance = new AccountDaoImpl();
                }
            }
        }
        return  localInstance;
    }

    @Override
    public Account add(Account account) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_ACCOUNT)) {
            statement.setInt(1, account.getUserId());
            statement.setString(2, account.getFirstName());
            statement.setString(3, account.getSecondName());
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
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
            throw new DaoException(e);
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
            throw new DaoException(e);
        }
        return account;
    }

    private Account createAccount(ResultSet resultSet) throws SQLException{
        return new Account(resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5));
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
            throw new DaoException(e);
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
            throw new DaoException(e);
        }
    }
}
