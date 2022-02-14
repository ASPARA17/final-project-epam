package com.epam.jwd.service.dto.userdto;

import com.epam.jwd.dao.entity.user.UserRole;
import com.epam.jwd.service.dto.EntityDto;

import java.util.Objects;

public class UserDto extends EntityDto<Integer> {
    private String login;
    private String password;
    private UserRole role;

    public UserDto() {
    }

    public UserDto(Integer id, String login, String password, int roleId) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = UserRole.getRoleById(roleId);
    }

    public UserDto(Integer id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserDto(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
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
        UserDto userDto = (UserDto) o;
        return Objects.equals(login, userDto.login) && Objects.equals(password, userDto.password) && role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role);
    }

    @Override
    public String toString() {
        return "UserDto{" + "login='" + login + '\'' + ", password='" + password + '\'' + ", " +
                "role=" + role + "} " + super.toString();
    }
}
