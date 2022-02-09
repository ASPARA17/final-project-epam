package com.epam.jwd.dao.api;

import com.epam.jwd.dao.entity.order.Order;
import com.epam.jwd.dao.exception.DaoException;

import java.sql.Date;
import java.util.List;

public interface OrderDao extends BaseDao<Order, Integer>{
    List<Order> findByStatus(String status) throws DaoException;
    void updateStatusById(Integer statusId, Integer id) throws DaoException;
    List<Order> findAllByAccountId(Integer id) throws DaoException;
    void updateReturnDateById(Date returnDate, Integer id) throws DaoException;
    List<Order> findOrdersToPageByAccountId(int page, int totalOrdersOnPage, Integer accountId) throws DaoException;
    List<Order> findAllToPage(int page, int totalOrdersOnPage) throws DaoException;

}
