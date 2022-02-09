package com.epam.jwd.service.impl;

import com.epam.jwd.dao.api.OrderDao;
import com.epam.jwd.dao.entity.order.Order;
import com.epam.jwd.dao.entity.order.OrderStatus;
import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.dao.impl.OrderDaoImpl;
import com.epam.jwd.service.api.OrderService;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.converter.impl.OrderConverter;
import com.epam.jwd.service.dto.orderdto.OrderDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.DateUtil;
import com.epam.jwd.service.validator.DateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final Converter<Order, OrderDto, Integer> converter;
    private final DateValidator validator;
    private static OrderServiceImpl instance = new OrderServiceImpl();

    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);

    private OrderServiceImpl() {
        this.orderDao = OrderDaoImpl.getInstance();
        this.converter = new OrderConverter();
        this.validator = new DateValidator();
    }

    public static OrderServiceImpl getInstance() {
        return instance;
    }

    @Override
    public OrderDto makeOrder(OrderDto orderDto) throws ServiceException {
        //todo add validator
        log.info("service " );
        Order createdOrder = converter.convert(orderDto);
        try {
            orderDto = converter.convert(orderDao.add(createdOrder));
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return orderDto;
    }

    @Override
    public List<OrderDto> findAll() throws ServiceException {
        List<OrderDto> orderDto = new ArrayList<>();
        try {
            for (Order order : orderDao.findAll()) {
                orderDto.add(converter.convert(order));
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return orderDto;
    }

    @Override
    public Optional<OrderDto> findById(Integer id) throws ServiceException {
        Optional<OrderDto> orderDtoOptional = Optional.empty();
        try {
            Optional<Order> order = orderDao.findById(id);
            if (order.isPresent()) {
                OrderDto orderDto = converter.convert(order.get());
                orderDtoOptional = Optional.of(orderDto);
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return orderDtoOptional;
    }

    @Override
    public List<OrderDto> findAllByAccountId(Integer accountId) throws ServiceException {
        List<OrderDto> orderDto = new ArrayList<>();
        try {
            for (Order order : orderDao.findAllByAccountId(accountId)) {
                orderDto.add(converter.convert(order));
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return orderDto;
    }

    @Override
    public List<OrderDto> findOrdersToPageByAccountId(int page, int totalOrdersOnPage,
                                                      Integer accountId) throws ServiceException {
        List<OrderDto> orderDtoOnPage = new ArrayList<>();
        try {
            for (Order order : orderDao.findOrdersToPageByAccountId(page, totalOrdersOnPage,
                    accountId)) {
                orderDtoOnPage.add(converter.convert(order));
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return orderDtoOnPage;
    }

    @Override
    public void cancelOrder(Integer orderId) throws ServiceException {
        Integer statusId = OrderStatus.CANCELED.getStatusId();
        try {
            orderDao.updateStatusById(statusId, orderId);
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException();
        }
    }

    @Override
    public List<OrderDto> findAllOrdersToPage(int page, int totalOrdersOnPage) throws ServiceException {
        List<OrderDto> orderDtoOnPage = new ArrayList<>();
        try {
            for (Order order : orderDao.findAllToPage(page, totalOrdersOnPage)) {
                orderDtoOnPage.add(converter.convert(order));
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return orderDtoOnPage;
    }

    @Override
    public void returnBook(Integer orderId) throws ServiceException {
        java.util.Date returnDateUtil = DateUtil.takeCurrentDateFormat();
        java.sql.Date returnDate = new java.sql.Date(returnDateUtil.getTime());
        Integer statusId = OrderStatus.COMPLETED.getStatusId();
        try {
            orderDao.updateReturnDateById(returnDate,orderId);
            orderDao.updateStatusById(statusId, orderId);
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException();
        }
    }

    @Override
    public void confirmOrder(Integer orderId) throws ServiceException {
        Integer statusId = OrderStatus.ACTIVE.getStatusId();
        try {
            orderDao.updateStatusById(statusId, orderId);
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException();
        }
    }
}
