package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.Order;
import com.epam.jwd.dao.exception.DaoException;

import java.util.List;

public interface OrderDao extends BaseDao<Order, Integer>{
    List<Order> findByStatus(String status) throws DaoException;
    void updateStatusById(Integer id) throws DaoException;
    List<Order> findAllByAccountId(Integer id) throws DaoException;

}
