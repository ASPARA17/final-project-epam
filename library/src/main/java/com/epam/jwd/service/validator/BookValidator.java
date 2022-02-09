package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.bookdto.BookDto;
import com.epam.jwd.service.exception.ServiceException;

//todo validator

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
    public boolean validate(BookDto bookDto) throws ServiceException {
        return isValidateAuthor(bookDto.getAuthor())
                && isValidateName(bookDto.getName())
                && isValidatePublishingHouse(bookDto.getPublishingHouse())
                && isValidateYearPublishing(Integer.toString(bookDto.getYearPublishing()))
                && isValidateNumberOfPage(Integer.toString(bookDto.getNumberOfPage()))
                && isValidateQuantity(Integer.toString(bookDto.getQuantity()));
    }

    private boolean isValidateAuthor (String author) {
        return isEmptyOrNull(author)
                && author.matches(AUTHOR_REGEX);
    }

    private boolean isValidateName(String name) {
        return isEmptyOrNull(name)
                && name.matches(NAME_BOOK_REGEX);
    }

    private boolean isValidatePublishingHouse(String publishingHouse) {
        return isEmptyOrNull(publishingHouse)
                && publishingHouse.matches(PUBLISHING_HOUSE_REGEX);
    }

    private boolean isValidateYearPublishing(String yearPublishing) {
        if (isEmptyOrNull(yearPublishing) && yearPublishing.matches(YEAR_PUBLISHING_REGEX)) {
            int currentYearPublishing = Integer.parseInt(yearPublishing);
            return DateUtil.getCurrentYear() <= currentYearPublishing;
        }
        return false;
    }

    private boolean isValidateNumberOfPage(String numberOfPage) {
        if (isEmptyOrNull(numberOfPage) && numberOfPage.matches(NUMBER_OF_PAGE_REGEX)) {
            int currentNumberOfPage = Integer.parseInt(numberOfPage);
            return MIN_NUMBER_OF_PAGE <= currentNumberOfPage && currentNumberOfPage <= MAX_NUMBER_OF_PAGE;
        }
        return false;
    }

    private boolean isValidateQuantity(String quantity) {
        if (isEmptyOrNull(quantity) && quantity.matches(QUANTITY_REGEX)) {
            int currentQuantity = Integer.parseInt(quantity);
            return MIN_QUANTITY <= currentQuantity && currentQuantity <= MAX_QUANTITY;
        }
        return false;
    }

        private boolean isEmptyOrNull(String str) {
            return str != null && !str.isEmpty();
        }

}
