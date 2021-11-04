package com.epam.jwd.dao.entity;

import java.util.Arrays;

public enum UserRole {
    ADMIN(1),
    USER(2);

    private int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserRole getRoleById(int id) {
        return Arrays.stream(UserRole.values())
                .filter(role -> role.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
