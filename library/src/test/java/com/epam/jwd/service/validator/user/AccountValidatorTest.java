package com.epam.jwd.service.validator.user;

import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.Validator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.jwd.service.exception.ExceptionMessage.*;
import static org.junit.Assert.assertThrows;
import static org.testng.Assert.assertEquals;

public class AccountValidatorTest {
    private Validator<AccountDto, Integer> validator;
    private static final AccountDto ACCOUNT_WITH_WRONG_NAME = new AccountDto
            .AccountDtoBuilder()
            .withFirstName("Mi")
            .withSecondName("Prokopovich")
            .withPhone("+375298420880")
            .withSubscriptionId("969632")
            .build();
    private static final AccountDto ACCOUNT_WITH_WRONG_SECOND_NAME = new AccountDto
            .AccountDtoBuilder()
            .withFirstName("Misha")
            .withSecondName("Proqwerthpmdyuqwerpkd")
            .withPhone("+375298420880")
            .withSubscriptionId("969632")
            .build();
    private static final AccountDto ACCOUNT_WITH_WRONG_PHONE = new AccountDto
            .AccountDtoBuilder()
            .withFirstName("Misha")
            .withSecondName("Prokopovich")
            .withPhone("+375408420880")
            .withSubscriptionId("969632")
            .build();
    private static final AccountDto ACCOUNT_WITH_WRONG_SUBSCRIPTION = new AccountDto
            .AccountDtoBuilder()
            .withFirstName("Misha")
            .withSecondName("Prokopovich")
            .withPhone("+375298420880")
            .withSubscriptionId("9a6963")
            .build();

    @BeforeClass
    public void beforeClass() {
        this.validator = new AccountValidator();
    }

    @Test
    void validateForWrongName() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(ACCOUNT_WITH_WRONG_NAME));
        assertEquals(exception.getMessage(), INVALID_FIRST_NAME_EXCEPTION);
    }

    @Test
    void validateForWrongSecondName() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(ACCOUNT_WITH_WRONG_SECOND_NAME));
        assertEquals(exception.getMessage(), INVALID_SECOND_NAME_EXCEPTION);
    }

    @Test
    void validateForWrongPhone() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(ACCOUNT_WITH_WRONG_PHONE));
        assertEquals(exception.getMessage(), INVALID_PHONE_EXCEPTION);
    }

    @Test
    void validateForWrongSubscription() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(ACCOUNT_WITH_WRONG_SUBSCRIPTION));
        assertEquals(exception.getMessage(), INVALID_SUBSCRIPTION_ID_EXCEPTION);
    }

}