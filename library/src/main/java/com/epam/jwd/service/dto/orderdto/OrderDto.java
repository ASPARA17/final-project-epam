package com.epam.jwd.service.dto.orderdto;

import com.epam.jwd.dao.entity.order.OrderStatus;
import com.epam.jwd.service.dto.EntityDto;
import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.dto.userdto.AccountDto;

import java.sql.Date;
import java.util.Objects;

public class OrderDto extends EntityDto<Integer> {
    private OrderStatus orderStatus;
    private AccountDto account;
    private BookDto book;
    private Date dateOfIssue;
    private Date returnDate;
    private boolean subscription;

    public OrderDto(Integer id) {
        super(id);
    }

    public OrderDto(Integer id, OrderStatus orderStatus, AccountDto account, BookDto book,
                    Date dateOfIssue, Date returnDate, boolean subscription) {
        super(id);
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

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return subscription == orderDto.subscription && orderStatus == orderDto.orderStatus && Objects.equals(account, orderDto.account) && Objects.equals(book, orderDto.book) && Objects.equals(dateOfIssue, orderDto.dateOfIssue) && Objects.equals(returnDate, orderDto.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderStatus, account, book, dateOfIssue, returnDate, subscription);
    }

    @Override
    public String toString() {
        return "OrderDto{" + "orderStatus=" + orderStatus + ", account=" + account + ", book=" + book + ", dateOfIssue=" + dateOfIssue + ", returnDate=" + returnDate + ", subscription=" + subscription + "} " + super.toString();
    }

    public static class OrderDtoBuilder {
        private Integer id;
        private OrderStatus orderStatus;
        private AccountDto account;
        private BookDto book;
        private Date dateOfIssue;
        private Date returnDate;
        private boolean subscription;

        public OrderDtoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public OrderDtoBuilder withOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderDtoBuilder withAccount(AccountDto account) {
            this.account = account;
            return this;
        }

        public OrderDtoBuilder withBook(BookDto book) {
            this.book = book;
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
            orderDto.setAccount(this.account);
            orderDto.setBook(this.book);
            orderDto.setDateOfIssue(this.dateOfIssue);
            orderDto.setReturnDate(this.returnDate);
            orderDto.setSubscription(this.subscription);
            return orderDto;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderDtoBuilder that = (OrderDtoBuilder) o;
            return subscription == that.subscription && Objects.equals(id, that.id) && orderStatus == that.orderStatus && Objects.equals(account, that.account) && Objects.equals(book, that.book) && Objects.equals(dateOfIssue, that.dateOfIssue) && Objects.equals(returnDate, that.returnDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, orderStatus, account, book, dateOfIssue, returnDate, subscription);
        }

        @Override
        public String toString() {
            return "OrderDtoBuilder{" + "id=" + id + ", orderStatus=" + orderStatus + ", account" +
                    "=" + account + ", book=" + book + ", dateOfIssue=" + dateOfIssue + ", " +
                    "returnDate=" + returnDate + ", subscription=" + subscription + '}';
        }
    }
}
