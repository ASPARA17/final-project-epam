package com.epam.jwd.service.api;

import com.epam.jwd.service.dto.orderdto.OrderDto;
import com.epam.jwd.service.exception.ServiceException;

import java.util.List;

public interface OrderService extends Service<OrderDto, Integer> {
    OrderDto makeOrder(OrderDto orderDto) throws ServiceException;
    List<OrderDto> findAllByAccountId(Integer accountId) throws ServiceException;
    List<OrderDto> findOrdersToPageByAccountId(int page, int totalOrdersOnPage,
                                               Integer accountId) throws ServiceException;
    void cancelOrder(Integer orderId) throws ServiceException;
    List<OrderDto> findAllOrdersToPage(int page, int totalOrdersOnPage) throws ServiceException;
    void returnBook(Integer orderId) throws ServiceException;
    void confirmOrder(Integer orderId) throws ServiceException;
}
