package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.UserDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.user.User;
import com.epam.jwd.dao.entity.user.UserRole;
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

public class UserDaoImpl implements UserDao {
    private static UserDao instance;
    private final ConnectionPool pool;
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    private UserDaoImpl() {
        this.pool = ConnectionPoolImpl.getInstance();
    }

    public static UserDao getInstance() {
        synchronized (UserDaoImpl.class) {
            if (instance == null) {
                instance = new UserDaoImpl();
                return instance;
            }
        }
        return instance;
    }

    @Override
    public User add(User user) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_USER,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().getRoleId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            log.error(CREATE_EXCEPTION, e);
            throw new DaoException(CREATE_EXCEPTION, e);
        }
        return user;
    }

    @Override
    public boolean delete(User entity) throws DaoException {
        return false;
    }

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
            log.error(FIND_ALL_EXCEPTION, e);
            throw new DaoException(FIND_ALL_EXCEPTION, e);
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
            log.error(FIND_BY_ID_EXCEPTION, e);
            throw new DaoException(FIND_BY_ID_EXCEPTION, e);
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
            log.error(FIND_BY_ID_EXCEPTION, e);
            throw new DaoException(FIND_BY_ID_EXCEPTION, e);
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
            log.error(UPDATE_EXCEPTION, e);
            throw new DaoException(UPDATE_EXCEPTION, e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SqlQuery.FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userLogin = resultSet.getString("login");
                String password = resultSet.getString("password");
                int roleId = resultSet.getInt("id_role");
                user = Optional.of(new User(id, userLogin, password, roleId));
            }
        } catch (SQLException e) {
            log.error(FIND_BY_ID_EXCEPTION, e);
            throw new DaoException(FIND_BY_ID_EXCEPTION, e);
        }
        return user;
    }
}
