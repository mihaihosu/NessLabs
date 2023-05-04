package com.nesslabs.nesslabspring.service;


import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    private RegistrationService registrationService;
    @Mock
    private EmailValidatorService emailValidatorService;
    @Mock
    private PasswordValidatorService passwordValidatorService;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        registrationService = new RegistrationService(emailValidatorService, passwordValidatorService, userService);
    }

    @Test
    void testRegister_WithValidInput_ShouldReturnNewUser() {
        RegistrationRequest request = RegistrationRequest
                .builder()
                .email("exemple@yahoo.com")
                .password("Password123")
                .username("Bob")
                .is_admin(true)
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
    void testRegister_WithInvalidEmail_ShouldThrowException() {
        RegistrationRequest request = RegistrationRequest
                .builder()
                .email("exemple")
                .password("Password123")
                .username("Bob")
                .is_admin(true)
                .build();
        when(emailValidatorService.test(request.getEmail())).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> registrationService.register(request));
        verify(emailValidatorService, times(1)).test(request.getEmail());
        verify(passwordValidatorService, never()).test(request.getPassword());
        verify(userService, never()).signUpUser(any(User.class));
    }

    @Test
    void testRegister_WithInvalidPassword_ShouldThrowException() {
        RegistrationRequest request = RegistrationRequest
                .builder()
                .email("exemple@yahoo.com")
                .password("Password")
                .username("Bob")
                .is_admin(true)
                .build();
        when(emailValidatorService.test(request.getEmail())).thenReturn(true);
        when(passwordValidatorService.test(request.getPassword())).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> registrationService.register(request));
        verify(emailValidatorService, times(1)).test(request.getEmail());
        verify(passwordValidatorService, times(1)).test(request.getPassword());
        verify(userService, never()).signUpUser(any(User.class));
    }

    @Test
    void testRegister_WithEmptyUsername_ShouldThrowException() {
        RegistrationRequest request = RegistrationRequest
                .builder()
                .email("exemple@yahoo.com")
                .password("Password123")
                .username("")
                .is_admin(true)
                .build();
        when(emailValidatorService.test(request.getEmail())).thenReturn(true);
        when(passwordValidatorService.test(request.getPassword())).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> registrationService.register(request));
        verify(emailValidatorService, times(1)).test(request.getEmail());
        verify(passwordValidatorService, times(1)).test(request.getPassword());
        verify(userService, never()).signUpUser(any(User.class));
    }
}