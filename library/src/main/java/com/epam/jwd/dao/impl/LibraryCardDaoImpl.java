package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.BaseDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.user.LibraryCard;
import com.epam.jwd.dao.exception.DaoException;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LibraryCardDaoImpl implements BaseDao<LibraryCard, Integer> {
    private static LibraryCardDaoImpl instance = new LibraryCardDaoImpl();
    private ConnectionPool pool;

    private LibraryCardDaoImpl() {
        this.pool = ConnectionPoolImpl.getInstance();
    }

    public static LibraryCardDaoImpl getInstance() {
        return instance;
    }
    @Override
    public LibraryCard add(LibraryCard libraryCard) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_LIBRARY_CARD,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, libraryCard.getDateOfIssue());
            statement.setDate(2, libraryCard.getExpirationDate());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                libraryCard.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return libraryCard;
    }

    @Override
    public boolean delete(LibraryCard entity) throws DaoException {
        return false;
    }

    @Override
    public List<LibraryCard> findAll() throws DaoException {
        return Collections.emptyList();
    }

    @Override
    public Optional<LibraryCard> findById(Integer id) throws DaoException {
        Optional<LibraryCard> libraryCard = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_LIBRARY_CARD_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Date dateOfIssue = resultSet.getDate(2);
                Date expirationDate = resultSet.getDate(3);
                libraryCard = Optional.of(new LibraryCard(dateOfIssue, expirationDate));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return libraryCard;
    }
}
