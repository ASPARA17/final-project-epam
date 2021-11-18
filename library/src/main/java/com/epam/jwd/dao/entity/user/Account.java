package com.epam.jwd.dao.entity.user;

import com.epam.jwd.dao.entity.Entity;

public class Account extends Entity<Integer> {
    private User user;
    private String firstName;
    private String secondName;

    public Account(Integer id, User user, String firstName, String secondName) {
        super(id);
        this.user = user;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Account(User user, String firstName, String secondName) {
        this.user = user;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
