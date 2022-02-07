package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.OrderDao;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.order.Order;
import com.epam.jwd.dao.entity.order.OrderStatus;
import com.epam.jwd.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static OrderDaoImpl instance = new OrderDaoImpl();
    private ConnectionPool pool;

    private static final Logger log = LogManager.getLogger(OrderDaoImpl.class);

    private OrderDaoImpl() {
        this.pool = ConnectionPoolImpl.getInstance();
    }

    public static OrderDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Order add(Order order) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_ORDER,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getAccountId());
            statement.setInt(2, order.getBookId());
            statement.setDate(3, order.getDateOfIssue());
            statement.setBoolean(4, order.isSubscription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        log.info("dao " + order);
        return order;
    }

    @Override
    public boolean delete(Order entity) throws DaoException {
        return false;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> allOrder = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allOrder.add(createOrder(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return allOrder;
    }

    @Override
    public Optional<Order> findById(Integer id) throws DaoException {
        Optional<Order> order = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ORDER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = Optional.of(createOrder(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public List<Order> findByStatus(String status) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ORDER_BY_STATUS)) {
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderList.add(createOrder(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orderList;
    }

    @Override
    public void updateStatusById(Integer statusId, Integer id) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_STATUS_BY_ID)) {
            statement.setInt(1, statusId);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findAllByAccountId(Integer id) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_BY_ACCOUNT_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderList.add(createOrder(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orderList;
    }

    @Override
    public void updateReturnDateById(Date returnDate, Integer id) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_RETURN_DATE_BY_ID)) {
            statement.setDate(1, returnDate);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findOrdersToPageByAccountId(int page, int totalOrdersOnPage,
                                                   Integer accountId) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ON_PAGE_BY_ACCOUNT_ID)) {
            statement.setInt(1, accountId);
            statement.setInt(2,page - 1);
            statement.setInt(3, totalOrdersOnPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderList.add(createOrder(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orderList;
    }

    private Order createOrder(ResultSet resultSet) throws SQLException{
        return new Order.OrderBuilder()
                .withId(resultSet.getInt(1))
                .withOrderStatus(OrderStatus.getStatusById(resultSet.getInt(2)))
                .withAccountId(resultSet.getInt(3))
                .withBookId(resultSet.getInt(4))
                .withDateOfIssue(resultSet.getDate(5))
                .withReturnDate(resultSet.getDate(6))
                .withSubscription(resultSet.getBoolean(7))
                .build();
    }
}
