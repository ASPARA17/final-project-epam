package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.UserDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.dao.entity.user.UserRole;
import com.epam.jwd.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    // TODO: why singleton
    private static volatile UserDaoImpl instance;
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        UserDaoImpl localInstance = instance;
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                localInstance = instance;
                if (instance == null) {
                    instance = localInstance = new UserDaoImpl();
                }
            }
        }
        return  localInstance;
    }

    @Override
    public User add(User user) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().getRoleId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public boolean delete(User entity) throws DaoException {
        return false;
    }

    // TODO: think about creator
    @Override
    public List<User> findAll() throws DaoException {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_USERS)) {
            statement.setInt(1, UserRole.USER.getRoleId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                User user = new User(login, password);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return allUsers;
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                user = Optional.of(new User(login, password));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }


    @Override
    public String findPasswordByLogin(String login) throws DaoException {
        String password = null;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return password;
    }

    @Override
    public void updatePasswordByLogin(String password, String login) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD_BY_LOGIN)) {
            statement.setString(1, password);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
