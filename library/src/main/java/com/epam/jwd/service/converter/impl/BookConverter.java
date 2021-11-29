package com.epam.jwd.service.converter.impl;

import com.epam.jwd.dao.entity.book.Book;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.dto.bookdto.BookDto;

public class BookConverter implements Converter<Book, BookDto, Integer> {
    @Override
    public Book convert(BookDto entity) {
        return new Book.BookBuilder()
                .withId(entity.getId())
                .withGenre(entity.getGenre())
                .withAuthor(entity.getAuthor())
                .withName(entity.getName())
                .withPublishingHouse(entity.getPublishingHouse())
                .withYearPublishing(entity.getYearPublishing())
                .withNumberOfPage(entity.getNumberOfPage())
                .withQuantity(entity.getQuantity())
                .build();
    }

    @Override
    public BookDto convert(Book entity) {
        return new BookDto.BookDtoBuilder()
                .withId(entity.getId())
                .withGenre(entity.getGenre())
                .withAuthor(entity.getAuthor())
                .withName(entity.getName())
                .withPublishingHouse(entity.getPublishingHouse())
                .withYearPublishing(entity.getYearPublishing())
                .withNumberOfPage(entity.getNumberOfPage())
                .withQuantity(entity.getQuantity())
                .build();
    }
}
