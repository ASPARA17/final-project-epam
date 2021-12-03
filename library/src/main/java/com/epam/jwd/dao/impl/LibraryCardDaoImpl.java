package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.BaseDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.user.LibraryCard;
import com.epam.jwd.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LibraryCardDaoImpl implements BaseDao<LibraryCard, Integer> {
    private static volatile LibraryCardDaoImpl instance;
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private LibraryCardDaoImpl() {
    }

    public static LibraryCardDaoImpl getInstance() {
        LibraryCardDaoImpl localInstance = instance;
        if (instance == null) {
            synchronized (LibraryCardDaoImpl.class) {
                localInstance = instance;
                if (instance == null) {
                    instance = localInstance = new LibraryCardDaoImpl();
                }
            }
        }
        return  localInstance;
    }
    @Override
    public LibraryCard add(LibraryCard libraryCard) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_LIBRARY_CARD)) {
            statement.setDate(1, libraryCard.getDateOfIssue());
            statement.setDate(2, libraryCard.getExpirationDate());
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
