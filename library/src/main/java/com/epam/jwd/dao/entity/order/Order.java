package com.epam.jwd.dao.entity.order;

import com.epam.jwd.dao.entity.Entity;

import java.sql.Date;
import java.util.Objects;

public class Order extends Entity<Integer> {
    private OrderStatus orderStatus;
    private Integer accountId;
    private Integer bookId;
    private Date dateOfIssue;
    private Date returnDate;
    private boolean subscription;

    public Order(Integer id) {
        super(id);
    }

    public Order(Integer id, OrderStatus orderStatus, Integer accountId, Integer bookId,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return subscription == order.subscription && orderStatus == order.orderStatus && Objects.equals(accountId, order.accountId) && Objects.equals(bookId, order.bookId) && Objects.equals(dateOfIssue, order.dateOfIssue) && Objects.equals(returnDate, order.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderStatus, accountId, bookId, dateOfIssue, returnDate, subscription);
    }

    @Override
    public String toString() {
        return "Order{" + "orderStatus=" + orderStatus + ", accountId=" + accountId + ", bookId=" + bookId + ", dateOfIssue=" + dateOfIssue + ", returnDate=" + returnDate + ", subscription=" + subscription + "} " + super.toString();
    }

    public static class OrderBuilder {
        private Integer id;
        private OrderStatus orderStatus;
        private Integer accountId;
        private Integer bookId;
        private Date dateOfIssue;
        private Date returnDate;
        private boolean subscription;

        public OrderBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public OrderBuilder withOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderBuilder withAccountId(Integer accountId) {
            this.accountId = accountId;
            return this;
        }

        public OrderBuilder withBookId(Integer bookId) {
            this.bookId = bookId;
            return this;
        }

        public OrderBuilder withDateOfIssue(Date dateOfIssue) {
            this.dateOfIssue = dateOfIssue;
            return this;
        }

        public OrderBuilder withReturnDate(Date returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public OrderBuilder withSubscription(boolean subscription) {
            this.subscription = subscription;
            return this;
        }

        public Order build() {
            Order order = new Order(this.id);
            order.setOrderStatus(this.orderStatus);
            order.setAccountId(this.accountId);
            order.setBookId(this.bookId);
            order.setDateOfIssue(this.dateOfIssue);
            order.setReturnDate(this.returnDate);
            order.setSubscription(this.subscription);
            return order;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderBuilder that = (OrderBuilder) o;
            return subscription == that.subscription && Objects.equals(id, that.id) && orderStatus == that.orderStatus && Objects.equals(accountId, that.accountId) && Objects.equals(bookId, that.bookId) && Objects.equals(dateOfIssue, that.dateOfIssue) && Objects.equals(returnDate, that.returnDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, orderStatus, accountId, bookId, dateOfIssue, returnDate, subscription);
        }

        @Override
        public String toString() {
            return "OrderBuilder{" + "id=" + id + ", orderStatus=" + orderStatus + ", accountId=" + accountId + ", bookId=" + bookId + ", dateOfIssue=" + dateOfIssue + ", returnDate=" + returnDate + ", subscription=" + subscription + '}';
        }
    }
}
