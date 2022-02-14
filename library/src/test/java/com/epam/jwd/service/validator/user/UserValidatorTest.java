package com.epam.jwd.service.validator.user;

import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.Validator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_LOGIN_EXCEPTION;
import static com.epam.jwd.service.exception.ExceptionMessage.INVALID_PASSWORD_EXCEPTION;
import static org.junit.Assert.assertThrows;
import static org.testng.Assert.assertEquals;

public class UserValidatorTest {

    private Validator<UserDto, Integer> validator;
    private static final UserDto USER_WITH_WRONG_LOGIN = new UserDto("700Q)", "mark789");
    private static final UserDto USER_WITH_WRONG_PASSWORD = new UserDto("mark90", "1");

    @BeforeClass
    public void beforeClass() {
        this.validator = new UserValidator();
    }

    @Test
    void validateForWrongLogin() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(USER_WITH_WRONG_LOGIN));
        assertEquals(exception.getMessage(), INVALID_LOGIN_EXCEPTION);
    }

    @Test
    void validateForWrongPassword() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> validator.validate(USER_WITH_WRONG_PASSWORD));
        assertEquals(exception.getMessage(), INVALID_PASSWORD_EXCEPTION);
    }



}