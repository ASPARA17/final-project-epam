package com.epam.jwd.dao.entity;

/**
 * Class which Entity which provides id of provided type
 * @param <T> type of id
 */
public class Entity<T> {
    /**
     * Field of generic type which provides id
     */
    private T id;

    /**
     * Constructor to create Object of current class
     * @see Entity#Entity(Object)
     */
    public Entity() {
    }

    /**
     * Constructor which creates object with initialized id field {@link Entity#id}
     * @param id id to initialize
     */
    public Entity(T id) {
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
        return "Entity{" + "id=" + id + '}';
    }
}
