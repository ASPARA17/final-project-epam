package com.epam.jwd.dao.entity.user;

import java.util.Arrays;

public enum UserRole {
    ADMIN(1),
    USER(2),
    GUEST(3);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return id;
    }

    public static UserRole getRoleById(int id) {
        return Arrays.stream(UserRole.values())
                .filter(role -> role.getRoleId() == id)
                .findFirst()
                .orElse(null);
    }
}
