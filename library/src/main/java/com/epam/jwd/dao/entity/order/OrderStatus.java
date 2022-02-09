package com.epam.jwd.dao.entity.order;

import java.util.Arrays;

public enum OrderStatus {
    WAITING(1),
    ACTIVE(2),
    CANCELED(3),
    COMPLETED(4);

    private final int statusId;

    OrderStatus(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public static OrderStatus getStatusById(int statusId) {
        return Arrays.stream(OrderStatus.values())
                .filter(status -> status.getStatusId() == statusId)
                .findFirst()
                .orElse(null);
    }
}
