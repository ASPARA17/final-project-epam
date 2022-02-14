package com.epam.jwd.service.validator.book;

import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.date.DateUtil;
import com.epam.jwd.service.validator.Validator;

import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_AUTHOR_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_NAME_BOOK_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_PAGES_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_PUBLISHER_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_QUANTITY_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_YEAR_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.STR_EMPTY_OR_NULL_EXCEPTION;


public class BookValidator implements Validator<BookDto, Integer> {
    private static final int MIN_NUMBER_OF_PAGE = 1;
    private static final int MAX_NUMBER_OF_PAGE = 2000;
    private static final int MIN_QUANTITY = 0;
    private static final int MAX_QUANTITY = 50;
    private static final String QUANTITY_REGEX = "^\\d+$";
    private static final String NUMBER_OF_PAGE_REGEX = "^\\d+$";
    private static final String YEAR_PUBLISHING_REGEX = "^[\\d+]{1,4}$";
    private static final String PUBLISHING_HOUSE_REGEX = "^[a-zA-Z&\\-\\/,\\.\\s]{3,40}$";
    private static final String NAME_BOOK_REGEX = "^.{1,100}$";
    private static final String AUTHOR_REGEX = "^[a-zA-Z\\s]{3,40}$";

    @Override
    public void validate(BookDto bookDto) throws ServiceException {
        isValidateAuthor(bookDto.getAuthor());
        isValidateName(bookDto.getName());
        isValidatePublishingHouse(bookDto.getPublishingHouse());
        isValidateYearPublishing(Integer.toString(bookDto.getYearPublishing()));
        isValidateNumberOfPage(Integer.toString(bookDto.getNumberOfPage()));
        isValidateQuantity(Integer.toString(bookDto.getQuantity()));
    }

    private void isValidateAuthor (String author) throws ServiceException {
        isEmptyOrNull(author);
        if (!author.matches(AUTHOR_REGEX)) {
            throw new ServiceException(INVALID_AUTHOR_EXCEPTION);
        }
    }

    private void isValidateName(String name) throws ServiceException {
        isEmptyOrNull(name);
        if (!name.matches(NAME_BOOK_REGEX)) {
            throw new ServiceException(INVALID_NAME_BOOK_EXCEPTION);
        }
    }

    private void isValidatePublishingHouse(String publishingHouse) throws ServiceException {
        isEmptyOrNull(publishingHouse);
        if (!publishingHouse.matches(PUBLISHING_HOUSE_REGEX)) {
            throw new ServiceException(INVALID_PUBLISHER_EXCEPTION);
        }
    }

    private void isValidateYearPublishing(String yearPublishing) throws ServiceException {
        isEmptyOrNull(yearPublishing);
        int currentYearPublishing = Integer.parseInt(yearPublishing);
        if (!yearPublishing.matches(YEAR_PUBLISHING_REGEX)
                || (DateUtil.getCurrentYear() < currentYearPublishing)) {
            throw new ServiceException(INVALID_YEAR_EXCEPTION);
        }
    }

    private void isValidateNumberOfPage(String numberOfPage) throws ServiceException {
        isEmptyOrNull(numberOfPage);
        int currentNumberOfPage = Integer.parseInt(numberOfPage);
        if (!numberOfPage.matches(NUMBER_OF_PAGE_REGEX)
                || (MIN_NUMBER_OF_PAGE > currentNumberOfPage
                || currentNumberOfPage > MAX_NUMBER_OF_PAGE)) {
            throw new ServiceException(INVALID_PAGES_EXCEPTION);
        }
    }

    public void isValidateQuantity(String quantity) throws ServiceException {
        isEmptyOrNull(quantity);
        int currentQuantity = Integer.parseInt(quantity);
        if (!quantity.matches(QUANTITY_REGEX)
                || (MIN_QUANTITY > currentQuantity
                || currentQuantity > MAX_QUANTITY)) {
            throw new ServiceException(INVALID_QUANTITY_EXCEPTION);
        }
    }

    private void isEmptyOrNull(String str) throws  ServiceException {
        if (str == null || str.isEmpty()) {
            throw new ServiceException(STR_EMPTY_OR_NULL_EXCEPTION);
        }
    }

}
