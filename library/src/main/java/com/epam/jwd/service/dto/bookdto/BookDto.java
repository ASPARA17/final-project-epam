package com.epam.jwd.service.dto.bookdto;

import com.epam.jwd.dao.entity.book.Genre;
import com.epam.jwd.service.dto.EntityDto;

public class BookDto extends EntityDto<Integer> {
    private Genre genre;
    private String author;
    private String name;
    private String publishingHouse;
    private int yearPublishing;
    private int numberOfPage;
    private int quantity;

    public BookDto(Integer id) {
        super(id);
    }

    public BookDto(Integer id, Genre genre, String author, String name, String publishingHouse,
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

    public static class BookDtoBuilder {
        private Integer id;
        private Genre genre;
        private String author;
        private String name;
        private String publishingHouse;
        private int yearPublishing;
        private int numberOfPage;
        private int quantity;

        public BookDtoBuilder() {
        }

        public BookDtoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public BookDtoBuilder withGenre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public BookDtoBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public BookDtoBuilder withPublishingHouse(String publishingHouse) {
            this.publishingHouse = publishingHouse;
            return this;
        }

        public BookDtoBuilder withYearPublishing(int yearPublishing) {
            this.yearPublishing = yearPublishing;
            return this;
        }

        public BookDtoBuilder withNumberOfPage(int numberOfPage) {
            this.numberOfPage = numberOfPage;
            return this;
        }

        public BookDtoBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public BookDto build() {
            BookDto bookDto = new BookDto(this.id);
            bookDto.setGenre(this.genre);
            bookDto.setAuthor(this.author);
            bookDto.setName(this.name);
            bookDto.setPublishingHouse(this.publishingHouse);
            bookDto.setYearPublishing(this.yearPublishing);
            bookDto.setNumberOfPage(this.numberOfPage);
            bookDto.setQuantity(this.quantity);
            return bookDto;
        }
    }

}
