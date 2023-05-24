package com.nesslabs.nesslabspring.service;


import com.nesslabs.nesslabspring.dto.RegistrationDto;
import com.nesslabs.nesslabspring.exception.InvalidCredentialException;
import com.nesslabs.nesslabspring.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    private RegistrationServiceImpl registrationService;
    @Mock
    private EmailValidatorService emailValidatorService;
    @Mock
    private PasswordValidatorService passwordValidatorService;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        registrationService = new RegistrationServiceImpl(emailValidatorService, passwordValidatorService, userService);
    }

    @Test
    void testRegister_WithValidInput_ShouldReturnNewUser() throws InvalidCredentialException {
        RegistrationDto request = RegistrationDto
                .builder()
                .email("exemple@yahoo.com")
                .password("Password123")
                .username("Bob")
                .admin(true)
                .build();

        when(emailValidatorService.test(request.getEmail())).thenReturn(true);
        when(passwordValidatorService.test(request.getPassword())).thenReturn(true);
        when(userService.signUpUser(any(User.class))).thenReturn(new User());

        User result = registrationService.register(request);

        assertNotNull(result);
        verify(emailValidatorService, times(1)).test(request.getEmail());
        verify(passwordValidatorService, times(1)).test(request.getPassword());
        verify(userService, times(1)).signUpUser(any(User.class));
    }

    @Test
    void testRegister_WithInvalidEmail_ShouldThrowException(){
        RegistrationDto request = RegistrationDto
                .builder()
                .email("exemple")
                .password("Password123")
                .username("Bob")
                .admin(true)
                .build();
        when(emailValidatorService.test(anyString())).thenReturn(false);
        assertThrows(InvalidCredentialException.class, () -> {
            registrationService.register(request);
        });
    }

    @Test
    void testRegister_WithInvalidPassword_ShouldThrowException() throws InvalidCredentialException {
        RegistrationDto request = RegistrationDto
                .builder()
                .email("exemple@yahoo.com")
                .password("Password")
                .username("Bob")
                .admin(true)
                .build();
        when(emailValidatorService.test(anyString())).thenReturn(true);
        when(passwordValidatorService.test(anyString())).thenReturn(false);

        assertThrows(InvalidCredentialException.class, () -> {
            registrationService.register(request);
        });
    }

    @Test
    void testRegister_WithEmptyUsername_ShouldThrowException() throws InvalidCredentialException {
        RegistrationDto request = RegistrationDto
                .builder()
                .email("exemple@yahoo.com")
                .password("Password123")
                .username("")
                .admin(true)
                .build();
        when(emailValidatorService.test(anyString())).thenReturn(true);
        when(passwordValidatorService.test(anyString())).thenReturn(true);

        assertThrows(InvalidCredentialException.class, () -> {
            registrationService.register(request);
        });
    }
}