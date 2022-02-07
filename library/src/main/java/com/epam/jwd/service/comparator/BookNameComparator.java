package com.epam.jwd.service.comparator;

import com.epam.jwd.service.dto.bookdto.BookDto;

import java.util.Comparator;

public class BookNameComparator implements Comparator<BookDto> {
    private static final BookNameComparator instance = new BookNameComparator();

    private BookNameComparator() {
    }

    public static BookNameComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(BookDto book1, BookDto book2) {
        return book1.getName().compareTo(book2.getName());
    }
}
