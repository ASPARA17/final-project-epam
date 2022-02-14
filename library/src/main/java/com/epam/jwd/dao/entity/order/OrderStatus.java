package com.epam.jwd.dao.entity.order;

import java.util.Arrays;

/**
 * Enum which describes available order status in library
 */
public enum OrderStatus {
    WAITING(1),
    ACTIVE(2),
    CANCELED(3),
    COMPLETED(4);

    /**
     * Integer field which describes status id
     */
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
