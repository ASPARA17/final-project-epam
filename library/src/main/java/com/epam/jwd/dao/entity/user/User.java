package com.epam.jwd.dao.entity.user;

import com.epam.jwd.dao.entity.Entity;

import java.util.Objects;

public class User extends Entity<Integer> {
    private String login;
    private String password;
    private UserRole role;

    public User() {
    }

    public User(Integer id, String login, String password, int roleId) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = UserRole.getRoleById(roleId);
    }

    public User(Integer id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role + '}';
    }
}
