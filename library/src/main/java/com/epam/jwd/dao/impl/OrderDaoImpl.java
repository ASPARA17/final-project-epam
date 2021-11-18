package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.api.OrderDao;
import com.epam.jwd.dao.entity.order.Order;
import com.epam.jwd.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order entity) throws DaoException {
        return null;
    }

    @Override
    public Order delete(Order entity) throws DaoException {
        return null;
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
    public void updateStatusById(Integer id) throws DaoException {
        // while nothing
    }

    @Override
    public List<Order> findAllByAccountId(Integer id) throws DaoException {
        return null;
    }
}
