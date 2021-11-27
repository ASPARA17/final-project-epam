package com.epam.jwd.service.dto.orderdto;

import com.epam.jwd.dao.entity.order.OrderStatus;
import com.epam.jwd.service.dto.EntityDto;

import java.sql.Date;

public class OrderDto extends EntityDto<Integer> {
    private OrderStatus orderStatus;
    private Integer accountId;
    private Integer bookId;
    private Date dateOfIssue;
    private Date returnDate;
    private boolean subscription;

    public OrderDto(Integer id) {
        super(id);
    }

    public OrderDto(Integer id, OrderStatus orderStatus, Integer accountId, Integer bookId,
                 Date dateOfIssue, Date returnDate, boolean subscription) {
        super(id);
        this.orderStatus = orderStatus;
        this.accountId = accountId;
        this.bookId = bookId;
        this.dateOfIssue = dateOfIssue;
        this.returnDate = returnDate;
        this.subscription = subscription;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public static class OrderDtoBuilder {
        private Integer id;
        private OrderStatus orderStatus;
        private Integer accountId;
        private Integer bookId;
        private Date dateOfIssue;
        private Date returnDate;
        private boolean subscription;

        public OrderDtoBuilder() {
        }

        public OrderDtoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public OrderDtoBuilder withOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderDtoBuilder withAccountId(Integer accountId) {
            this.accountId = accountId;
            return this;
        }

        public OrderDtoBuilder withBookId(Integer bookId) {
            this.bookId = bookId;
            return this;
        }

        public OrderDtoBuilder withDateOfIssue(Date dateOfIssue) {
            this.dateOfIssue = dateOfIssue;
            return this;
        }

        public OrderDtoBuilder withReturnDate(Date returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public OrderDtoBuilder withSubscription(boolean subscription) {
            this.subscription = subscription;
            return this;
        }

        public OrderDto build() {
            OrderDto orderDto = new OrderDto(this.id);
            orderDto.setOrderStatus(this.orderStatus);
            orderDto.setAccountId(this.accountId);
            orderDto.setBookId(this.bookId);
            orderDto.setDateOfIssue(this.dateOfIssue);
            orderDto.setReturnDate(this.returnDate);
            orderDto.setSubscription(this.subscription);
            return orderDto;
        }
    }
}
