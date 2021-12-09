package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.OrderDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.order.Order;
import com.epam.jwd.dao.exception.DaoException;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static volatile OrderDaoImpl instance;
    private ConnectionPool pool;

    private OrderDaoImpl() {
        this.pool = ConnectionPoolImpl.getInstance();
    }

    public static OrderDaoImpl getInstance() {
        OrderDaoImpl localInstance = instance;
        if (instance == null) {
            synchronized (OrderDaoImpl.class) {
                localInstance = instance;
                if (instance == null) {
                    instance = localInstance = new OrderDaoImpl();
                }
            }
        }
        return  localInstance;
    }

    @Override
    public Order add(Order order) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_ORDER,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getOrderStatus().getStatusId());
            statement.setInt(2, order.getAccountId());
            statement.setInt(3, order.getBookId());
            statement.setDate(4, order.getDateOfIssue());
            statement.setBoolean(5, order.isSubscription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public boolean delete(Order entity) throws DaoException {
        return false;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Order> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Order> findByStatus(String status) throws DaoException {
        return null;
    }

    @Override
    public void updateStatusById(Integer statusId, Integer id) throws DaoException {
        // while nothing
    }

    @Override
    public List<Order> findAllByAccountId(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void updateReturnDateById(Date returnDate, Integer id) throws DaoException {

    }
}
