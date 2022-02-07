package com.epam.jwd.service.comparator;

import com.epam.jwd.service.dto.bookdto.BookDto;

import java.util.Comparator;

public class BookQuantityComparator implements Comparator<BookDto> {
    private static final BookQuantityComparator instance = new BookQuantityComparator();

    private BookQuantityComparator() {
    }

    public static BookQuantityComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(BookDto book1, BookDto book2) {
        return Integer.compare(book1.getQuantity(), book2.getQuantity());
    }
}
