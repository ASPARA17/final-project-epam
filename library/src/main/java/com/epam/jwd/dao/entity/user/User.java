package com.epam.jwd.dao.entity.user;

import com.epam.jwd.dao.entity.Entity;

import java.util.Objects;

/**
 * User entity class which extends Entity with Integer id field
 * Class describes user of library
 */
public class User extends Entity<Integer> {
    /**
     * String field with user's login
     */
    private String login;
    /**
     * String filed with user's password
     */
    private String password;
    /**
     * UserRole field with user's role
     * @see UserRole
     */
    private UserRole role;

    /**
     * Constructor without arguments for creating empty User Object
     * @see User#User(Integer, String, String, int)
     * @see User#User(String, String)
     */
    public User() {
    }

    /**
     * Constructor with all amount of arguments for creating User object with all properties
     * @param id        user's id
     * @param login     user's login
     * @param password  user's password
     * @param roleId    user's roleId
     */
    public User(Integer id, String login, String password, int roleId) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = UserRole.getRoleById(roleId);
    }

    /**
     * Constructor for creating User object with login and password
     * @param login     user's login
     * @param password  user's password
     */
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
        return "User{" + "login='" + login + '\'' + ", password='" + password + '\'' + ", role=" + role + "} " + super.toString();
    }
}
