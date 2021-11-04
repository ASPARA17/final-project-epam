package com.epam.jwd.dao.entity;

public class Entity<T> {
    private T id;

    public Entity() {
    }

    public Entity(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
