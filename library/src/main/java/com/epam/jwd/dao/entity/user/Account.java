package com.epam.jwd.dao.entity.user;

import com.epam.jwd.dao.entity.Entity;

public class Account extends Entity<Integer> {
    private Integer userId;
    private String firstName;
    private String secondName;
    private String phone;
    private Integer subscriptionId;

    public Account(Integer id) {
        super(id);
    }

    public Account(Integer id, Integer userId, String firstName, String secondName, String phone,
                   Integer subscriptionId) {
        super(id);
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.subscriptionId = subscriptionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public static class AccountBuilder {
        private Integer id;
        private Integer userId;
        private String firstName;
        private String secondName;
        private String phone;
        private Integer subscriptionId;

        public AccountBuilder() {
        }

        public AccountBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public AccountBuilder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public AccountBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AccountBuilder withSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public AccountBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public AccountBuilder withSubscriptionId(Integer subscriptionId) {
            this.subscriptionId = subscriptionId;
            return this;
        }

        public Account build() {
            Account account = new Account(this.id);
            account.setUserId(this.userId);
            account.setFirstName(this.firstName);
            account.setSecondName(this.secondName);
            account.setPhone(this.phone);
            account.setSubscriptionId(this.subscriptionId);
            return account;
        }
    }


}
