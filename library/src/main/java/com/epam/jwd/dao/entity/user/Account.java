package com.epam.jwd.dao.entity.user;

import com.epam.jwd.dao.entity.Entity;

import java.util.Objects;

/**
 * Account entity class which extends Entity with Integer id field
 * Class describes account of library
 */
public class Account extends Entity<Integer> {
    /**
     * Integer field with user id which associated with current account
     */
    private Integer userId;
    /**
     * String field with account's first name
     */
    private String firstName;
    /**
     * String field with account's second name
     */
    private String secondName;
    /**
     * String field with account's phone
     */
    private String phone;
    /**
     * String field with account's subscription id
     */
    private String subscriptionId;

    /**
     * Constructor without arguments for creating empty Account object
     *
     * @see Account#Account(Integer)
     * @see Account#Account(Integer, Integer,String, String, String, String)
     */

    public Account() {
    }

    /**
     * Constructor with Integer id argument for creating Account with associated id property
     *
     * @param id account's id
     */
    public Account(Integer id) {
        super(id);
    }

    /**
     * Constructor with all amount of account's properties for creating account
     * @param id                account's id
     * @param userId            user id which associated with current account
     * @param firstName         account's first name
     * @param secondName        account's second name
     * @param phone             account's phone
     * @param subscriptionId    account's subscription id
     */
    public Account(Integer id, Integer userId, String firstName, String secondName, String phone,
                   String subscriptionId) {
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

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return userId.equals(account.userId) && firstName.equals(account.firstName) && secondName.equals(account.secondName) && phone.equals(account.phone) && Objects.equals(subscriptionId, account.subscriptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, secondName, phone, subscriptionId);
    }

    @Override
    public String toString() {
        return "Account{" + "userId=" + userId + ", firstName='" + firstName + '\'' + ", " +
                "secondName='" + secondName + '\'' + ", phone='" + phone + '\'' + ", " +
                "subscriptionId=" + subscriptionId + "} " + super.toString();
    }

    public static class AccountBuilder {
        private Integer id;
        private Integer userId;
        private String firstName;
        private String secondName;
        private String phone;
        private String subscriptionId;

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

        public AccountBuilder withSubscriptionId(String subscriptionId) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AccountBuilder that = (AccountBuilder) o;
            return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && Objects.equals(phone, that.phone) && Objects.equals(subscriptionId, that.subscriptionId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, userId, firstName, secondName, phone, subscriptionId);
        }

        @Override
        public String toString() {
            return "AccountBuilder{" + "id=" + id + ", userId=" + userId + ", firstName='" + firstName + '\'' + ", secondName='" + secondName + '\'' + ", phone='" + phone + '\'' + ", subscriptionId=" + subscriptionId + '}';
        }
    }


}
