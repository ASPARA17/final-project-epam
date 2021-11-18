package com.epam.jwd.dao.entity.order;

import com.epam.jwd.dao.entity.Entity;
import com.epam.jwd.dao.entity.book.Book;
import com.epam.jwd.dao.entity.user.Account;

import java.sql.Date;

public class Order extends Entity<Integer> {
    //TODO: know when use entityId or object of Entity
    private OrderStatus orderStatus;
    private Account account;
    private Book book;
    private Date dateOfIssue;
    private Date returnDate;
    private boolean subscription;

    public Order(Integer id, OrderStatus orderStatus, Account account, Book book,
                 Date dateOfIssue, Date returnDate, boolean subscription) {
        super(id);
        this.orderStatus = orderStatus;
        this.account = account;
        this.book = book;
        this.dateOfIssue = dateOfIssue;
        this.returnDate = returnDate;
        this.subscription = subscription;
    }

    public Order(OrderStatus orderStatus, Account account, Book book, Date dateOfIssue,
                 Date returnDate, boolean subscription) {
        this.orderStatus = orderStatus;
        this.account = account;
        this.book = book;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
