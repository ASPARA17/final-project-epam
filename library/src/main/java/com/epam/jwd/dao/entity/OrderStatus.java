package com.epam.jwd.dao.entity;

import java.util.Arrays;

public enum OrderStatus {
    WAITING("waiting"),
    ACTIVE("active"),
    REJECTED("rejected");

    private final String nameStatus;

    OrderStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public static OrderStatus getStatusByName(String name) {
        return Arrays.stream(OrderStatus.values())
                .filter(status -> status.getNameStatus().equals(name))
                .findFirst()
                .orElse(null);
    }
}
