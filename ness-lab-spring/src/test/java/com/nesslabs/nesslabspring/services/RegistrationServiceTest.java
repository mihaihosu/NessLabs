package com.nesslabs.nesslabspring.services;

import org.mockito.Mock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;

public class RegistrationServiceTest {

    private RegistrationService registrationService;

    @Mock
    private EmailValidator emailValidator;

    @Mock
    private UserService userService;

    @Mock
    private ConfirmationTokenService confirmationTokenService;
}
