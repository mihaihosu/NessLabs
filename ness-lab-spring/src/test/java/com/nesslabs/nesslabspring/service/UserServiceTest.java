package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.exception.InvalidCredentialException;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;


    @Test
    public void testSignUpUser() throws InvalidCredentialException {

        User user = User.builder()
                .email("example@yahoo.com")
                .username("example")
                .password("Password1233")
                .is_admin(false)
                .build();

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("hashedpassword");


        User savedUser = userService.signUpUser(user);


        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals("hashedpassword", savedUser.getPassword());

        verify(userRepository, times(1)).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertEquals(user.getEmail(), capturedUser.getEmail());
        assertEquals("hashedpassword", capturedUser.getPassword());
    }

    @Test
    public void testSignUpUserThrowsExceptionWhenUserAlreadyExists() {
        // Arrange
        User user = User.builder()
                .email("example@yahoo.com")
                .username("example")
                .password("Password1233")
                .is_admin(false)
                .build();


        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertThrows(IllegalStateException.class, () -> userService.signUpUser(user));
    }

}