package com.epam.jwd.dao.entity.user;

import com.epam.jwd.dao.entity.Entity;

public class Account extends Entity<Integer> {
    private Integer userId;
    private String firstName;
    private String secondName;
    private String phone;

    public Account(Integer id, Integer userId, String firstName, String secondName, String phone) {
        super(id);
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
    }

    public Account(Integer id, Integer userId, String firstName, String secondName) {
        super(id);
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Account(Integer userId, String firstName, String secondName, String phone) {
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
    }

    public Account(Integer userId, String firstName, String secondName) {
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
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
}
