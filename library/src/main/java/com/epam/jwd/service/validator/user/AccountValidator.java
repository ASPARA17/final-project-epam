package com.epam.jwd.service.validator.user;

import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.Validator;

import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_FIRST_NAME_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_PHONE_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_SECOND_NAME_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_SUBSCRIPTION_ID_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.STR_EMPTY_OR_NULL_EXCEPTION;

public class AccountValidator implements Validator<AccountDto, Integer> {
    private static final String FIRST_NAME_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String SECOND_NAME_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String PHONE_REGEX = "^\\+375(25|44|29|33)\\d{7}$";
    private static final String SUBSCRIPTION_ID_REGEX = "(^[\\d+]{6}$)||(^$)";

    @Override
    public void validate(AccountDto entity) throws ServiceException {
        isValidateFirstName(entity.getFirstName());
        isValidateSecondName(entity.getSecondName());
        isValidatePhone(entity.getPhone());
        isValidateSubscription(entity.getSubscriptionId());
    }

    private void isValidateFirstName(String firstName) throws ServiceException {
        isEmptyOrNull(firstName);
        if (!firstName.matches(FIRST_NAME_REGEX)) {
            throw new ServiceException(INVALID_FIRST_NAME_EXCEPTION);
        }
    }

    private void isValidateSecondName(String secondName) throws ServiceException {
        isEmptyOrNull(secondName);
        if (!secondName.matches(SECOND_NAME_REGEX)) {
            throw new ServiceException(INVALID_SECOND_NAME_EXCEPTION);
        }
    }

    private void isValidatePhone(String phone) throws ServiceException {
        isEmptyOrNull(phone);
        if (!phone.matches(PHONE_REGEX)) {
            throw new ServiceException(INVALID_PHONE_EXCEPTION);
        }
    }

    private void isValidateSubscription(String subscriptionId) throws ServiceException {
        isEmptyOrNull(subscriptionId);
        if (!subscriptionId.matches(SUBSCRIPTION_ID_REGEX)) {
            throw new ServiceException(INVALID_SUBSCRIPTION_ID_EXCEPTION);
        }
    }

    private void isEmptyOrNull(String str) throws ServiceException{
        if (str == null || str.isEmpty()) {
            throw new ServiceException(STR_EMPTY_OR_NULL_EXCEPTION);
        }
    }
}
