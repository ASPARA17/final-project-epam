package com.epam.jwd.dao.entity.order;

import com.epam.jwd.dao.entity.Entity;

import java.sql.Date;

public class Order extends Entity<Integer> {
    private OrderStatus orderStatus;
    private Integer accountId;
    private Integer bookId;
    private Date dateOfIssue;
    private Date returnDate;
    private boolean subscription;

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

    public Order(OrderStatus orderStatus, Integer accountId, Integer bookId, Date dateOfIssue,
                 Date returnDate, boolean subscription) {
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
}
