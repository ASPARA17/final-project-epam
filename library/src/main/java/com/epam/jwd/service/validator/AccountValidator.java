package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ServiceException;

public class AccountValidator implements Validator<AccountDto, Integer> {
    private static final String FIRST_NAME_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String SECOND_NAME_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String PHONE_REGEX = "^\\+375(25|44|29|33)\\d{7}$";
    private static final String SUBSCRIPTION_ID = "(^[\\d+]{6}$)||(^0$)";

    @Override
    public boolean validate(AccountDto entity) throws ServiceException {
        return isValidateFirstName(entity.getFirstName())
                && isValidateSecondName(entity.getSecondName())
                && isValidatePhone(entity.getPhone());
    }

    private boolean isValidateFirstName(String firstName) throws ServiceException {
        return isEmptyOrNull(firstName)
                && firstName.matches(FIRST_NAME_REGEX);
    }

    private boolean isValidateSecondName(String secondName) throws ServiceException {
        return isEmptyOrNull(secondName)
                && secondName.matches(SECOND_NAME_REGEX);
    }

    private boolean isValidatePhone(String phone) throws ServiceException {
        return isEmptyOrNull(phone)
                && phone.matches(PHONE_REGEX);
    }

    private boolean isEmptyOrNull(String str) {
        return str != null && !str.isEmpty();
    }
}
