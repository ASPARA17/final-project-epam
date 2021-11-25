package com.epam.jwd.dao.entity.book;

import com.epam.jwd.dao.entity.Entity;

import java.sql.Date;

public class Book extends Entity<Integer> {
    private Genre genre;
    private String author;
    private String name;
    private String publishingHouse;
    private int yearPublishing;
    private int numberOfPage;
    private int quantity;

    // TODO: add Builder
    public Book(Integer id, Genre genre, String author, String name, String publishingHouse,
                int yearPublishing, int numberOfPage, int quantity) {
        super(id);
        this.genre = genre;
        this.author = author;
        this.name = name;
        this.publishingHouse = publishingHouse;
        this.yearPublishing = yearPublishing;
        this.numberOfPage = numberOfPage;
        this.quantity = quantity;
    }

    public Book(Genre genre, String author, String name, String publishingHouse,
                int yearPublishing, int numberOfPage, int quantity) {
        this.genre = genre;
        this.author = author;
        this.name = name;
        this.publishingHouse = publishingHouse;
        this.yearPublishing = yearPublishing;
        this.numberOfPage = numberOfPage;
        this.quantity = quantity;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public int getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(int yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
