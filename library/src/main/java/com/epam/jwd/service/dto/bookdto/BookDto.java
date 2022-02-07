package com.epam.jwd.service.dto.bookdto;

import com.epam.jwd.dao.entity.book.Genre;
import com.epam.jwd.service.dto.EntityDto;

import java.util.Objects;

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

//    public BookDto(Integer id, Genre genre, String author, String name, String publishingHouse,
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
        BookDto bookDto = (BookDto) o;
        return yearPublishing == bookDto.yearPublishing && numberOfPage == bookDto.numberOfPage && quantity == bookDto.quantity && genre == bookDto.genre && Objects.equals(author, bookDto.author) && Objects.equals(name, bookDto.name) && Objects.equals(publishingHouse, bookDto.publishingHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre, author, name, publishingHouse, yearPublishing, numberOfPage, quantity);
    }

    @Override
    public String toString() {
        return "BookDto{" + "genre=" + genre + ", author='" + author + '\'' + ", name='" + name + '\'' + ", publishingHouse='" + publishingHouse + '\'' + ", yearPublishing=" + yearPublishing + ", numberOfPage=" + numberOfPage + ", quantity=" + quantity + "} " + super.toString();
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BookDtoBuilder that = (BookDtoBuilder) o;
            return yearPublishing == that.yearPublishing && numberOfPage == that.numberOfPage && quantity == that.quantity && Objects.equals(id, that.id) && genre == that.genre && Objects.equals(author, that.author) && Objects.equals(name, that.name) && Objects.equals(publishingHouse, that.publishingHouse);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, genre, author, name, publishingHouse, yearPublishing, numberOfPage, quantity);
        }

        @Override
        public String toString() {
            return "BookDtoBuilder{" + "id=" + id + ", genre=" + genre + ", author='" + author + '\'' + ", name='" + name + '\'' + ", publishingHouse='" + publishingHouse + '\'' + ", yearPublishing=" + yearPublishing + ", numberOfPage=" + numberOfPage + ", quantity=" + quantity + '}';
        }
    }

}
