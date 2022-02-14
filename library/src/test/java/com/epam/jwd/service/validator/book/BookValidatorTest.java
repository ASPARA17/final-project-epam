package com.epam.jwd.service.validator.book;

import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.Validator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.jwd.service.exception.ExceptionMessage.*;
import static org.junit.Assert.assertThrows;
import static org.testng.Assert.assertEquals;

public class BookValidatorTest {
    private Validator<BookDto, Integer> validator;
    private static final BookDto BOOK_WITH_WRONG_AUTHOR = new BookDto
            .BookDtoBuilder()
            .withAuthor("1234")
            .withName("Sun-By 2")
            .withPublishingHouse("A, B&C")
            .withYearPublishing(2001)
            .withNumberOfPage(340)
            .withQuantity(5)
            .build();
    private static final BookDto BOOK_WITH_WRONG_NAME = new BookDto
            .BookDtoBuilder()
            .withAuthor("Sofya Val")
            .withName("")
            .withPublishingHouse("A, B&C")
            .withYearPublishing(2001)
            .withNumberOfPage(340)
            .withQuantity(5)
            .build();
    private static final BookDto BOOK_WITH_WRONG_PUBLISHER = new BookDto
            .BookDtoBuilder()
            .withAuthor("Sofya Val")
            .withName("Sun-By 2")
            .withPublishingHouse("A,_ B&C")
            .withYearPublishing(2001)
            .withNumberOfPage(340)
            .withQuantity(5)
            .build();
    private static final BookDto BOOK_WITH_WRONG_YEAR = new BookDto
            .BookDtoBuilder()
            .withAuthor("Sofya Val")
            .withName("Sun-By 2")
            .withPublishingHouse("A, B&C")
            .withYearPublishing(2030)
            .withNumberOfPage(340)
            .withQuantity(5)
            .build();
    private static final BookDto BOOK_WITH_WRONG_PAGES = new BookDto
            .BookDtoBuilder()
            .withAuthor("Sofya Val")
            .withName("Sun-By 2")
            .withPublishingHouse("A, B&C")
            .withYearPublishing(2001)
            .withNumberOfPage(2001)
            .withQuantity(5)
            .build();
    private static final BookDto BOOK_WITH_WRONG_QUANTITY = new BookDto
            .BookDtoBuilder()
            .withAuthor("Sofya Val")
            .withName("Sun-By 2")
            .withPublishingHouse("A, B&C")
            .withYearPublishing(2001)
            .withNumberOfPage(340)
            .withQuantity(-5)
            .build();


    @BeforeClass
    public void beforeClass() {
        this.validator = new BookValidator();
    }

    @Test
    void validateForWrongAuthor() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(BOOK_WITH_WRONG_AUTHOR));
        assertEquals(exception.getMessage(), INVALID_AUTHOR_EXCEPTION);
    }

    @Test
    void validateForWrongName() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(BOOK_WITH_WRONG_NAME));
        assertEquals(exception.getMessage(), STR_EMPTY_OR_NULL_EXCEPTION);
    }

    @Test
    void validateForWrongPublisher() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(BOOK_WITH_WRONG_PUBLISHER));
        assertEquals(exception.getMessage(), INVALID_PUBLISHER_EXCEPTION);
    }

    @Test
    void validateForWrongYear() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(BOOK_WITH_WRONG_YEAR));
        assertEquals(exception.getMessage(), INVALID_YEAR_EXCEPTION);
    }

    @Test
    void validateForWrongPages() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(BOOK_WITH_WRONG_PAGES));
        assertEquals(exception.getMessage(), INVALID_PAGES_EXCEPTION);
    }

    @Test
    void validateForWrongQuantity() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(BOOK_WITH_WRONG_QUANTITY));
        assertEquals(exception.getMessage(), INVALID_QUANTITY_EXCEPTION);
    }

}