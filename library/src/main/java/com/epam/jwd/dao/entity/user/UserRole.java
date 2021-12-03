package com.epam.jwd.dao.entity.user;

import java.util.Arrays;
import java.util.List;

public enum UserRole {
    ADMIN(1),
    USER(2),
    GUEST(3);

    private final int id;
    private static final List<UserRole> ALL_AVAILABLE_ROLES = Arrays.asList(values());

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

    public static List<UserRole> valuesAsList() {
        return ALL_AVAILABLE_ROLES;
    }
}
