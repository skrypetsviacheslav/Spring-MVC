package com.slava.util;

import com.slava.config.TestConfig;
import com.slava.model.User;
import com.slava.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class)
class UserValidatorTest {
    @Autowired
    private UserValidator userValidator;

    //mocked dependencies
    @Autowired
    private UserService userService;

    private static final String userEmail = "testEmail@gmail.com";
    private static final User user = mock(User.class);

    @BeforeAll
    public static void setup() {
        when(user.getEmail()).thenReturn(userEmail);
    }

    @Test
    public void validateShouldAcceptUserWithNewEmail() {
        when(userService.getByEmail(userEmail)).thenReturn(null);
        Errors errors = mock(Errors.class);

        userValidator.validate(user, errors);

        verify(errors, never()).rejectValue(eq("email"), any(), any());
    }

    @Test
    public void validateShouldRejectUserWIthAlreadyUsedEmail() {
        when(userService.getByEmail(userEmail)).thenReturn(user);
        Errors errors = mock(Errors.class);

        userValidator.validate(user, errors);

        verify(errors, times(1)).rejectValue(eq("email"), any(), any());
    }
}