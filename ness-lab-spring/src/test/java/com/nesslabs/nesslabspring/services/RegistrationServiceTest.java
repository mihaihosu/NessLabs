package com.nesslabs.nesslabspring.services;

import org.mockito.Mock;

public class RegistrationServiceTest {

    private RegistrationServiceImpl registrationServiceImpl;

    @Mock
    private EmailValidator emailValidator;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private ConfirmationTokenServiceImpl confirmationTokenService;
}
