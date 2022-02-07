package com.epam.jwd.dao.entity.book;

import com.epam.jwd.dao.entity.Entity;

import java.util.Objects;

public class Book extends Entity<Integer> {
    private Genre genre;
    private String author;
    private String name;
    private String publishingHouse;
    private int yearPublishing;
    private int numberOfPage;
    private int quantity;

    public Book(Integer id) {
        super(id);
    }

//    public Book(Integer id, Genre genre, String author, String name, String publishingHouse,
//                int yearPublishing, int numberOfPage, int quantity) {
//        super(id);
//        this.genre = genre;
//        this.author = author;
//        this.name = name;
//        this.publishingHouse = publishingHouse;
//        this.yearPublishing = yearPublishing;
//        this.numberOfPage = numberOfPage;
//        this.quantity = quantity;
//    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return yearPublishing == book.yearPublishing && numberOfPage == book.numberOfPage && quantity == book.quantity && genre == book.genre && Objects.equals(author, book.author) && Objects.equals(name, book.name) && Objects.equals(publishingHouse, book.publishingHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre, author, name, publishingHouse, yearPublishing, numberOfPage, quantity);
    }

    @Override
    public String toString() {
        return "Book{" + "genre=" + genre + ", author='" + author + '\'' + ", name='" + name + '\'' + ", publishingHouse='" + publishingHouse + '\'' + ", yearPublishing=" + yearPublishing + ", numberOfPage=" + numberOfPage + ", quantity=" + quantity + "} " + super.toString();
    }

    public static class BookBuilder {
        private Integer id;
        private Genre genre;
        private String author;
        private String name;
        private String publishingHouse;
        private int yearPublishing;
        private int numberOfPage;
        private int quantity;

        public BookBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public BookBuilder withGenre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public BookBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public BookBuilder withPublishingHouse(String publishingHouse) {
            this.publishingHouse = publishingHouse;
            return this;
        }

        public BookBuilder withYearPublishing(int yearPublishing) {
            this.yearPublishing = yearPublishing;
            return this;
        }

        public BookBuilder withNumberOfPage(int numberOfPage) {
            this.numberOfPage = numberOfPage;
            return this;
        }

        public BookBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Book build() {
            Book book = new Book(this.id);
            book.setGenre(this.genre);
            book.setAuthor(this.author);
            book.setName(this.name);
            book.setPublishingHouse(this.publishingHouse);
            book.setYearPublishing(this.yearPublishing);
            book.setNumberOfPage(this.numberOfPage);
            book.setQuantity(this.quantity);
            return book;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BookBuilder that = (BookBuilder) o;
            return yearPublishing == that.yearPublishing && numberOfPage == that.numberOfPage && quantity == that.quantity && Objects.equals(id, that.id) && genre == that.genre && Objects.equals(author, that.author) && Objects.equals(name, that.name) && Objects.equals(publishingHouse, that.publishingHouse);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, genre, author, name, publishingHouse, yearPublishing, numberOfPage, quantity);
        }

        @Override
        public String toString() {
            return "BookBuilder{" + "id=" + id + ", genre=" + genre + ", author='" + author + '\'' + ", name='" + name + '\'' + ", publishingHouse='" + publishingHouse + '\'' + ", yearPublishing=" + yearPublishing + ", numberOfPage=" + numberOfPage + ", quantity=" + quantity + '}';
        }
    }
}
