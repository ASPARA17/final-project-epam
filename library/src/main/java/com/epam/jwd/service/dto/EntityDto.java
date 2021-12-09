package com.epam.jwd.service.dto;

public class EntityDto<T> {
    private T id;

    public EntityDto() {
    }

    public EntityDto(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EntityDto{" + "id=" + id + '}';
    }
}
