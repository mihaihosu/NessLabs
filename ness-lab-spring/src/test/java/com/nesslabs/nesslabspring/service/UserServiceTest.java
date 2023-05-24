package com.nesslabs.nesslabspring.service;
import com.nesslabs.nesslabspring.exception.InvalidCredentialException;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSignUpUserWithValidUser() throws InvalidCredentialException {
        // Arrange
        User user = new User();
        user.setEmail("test@test.com");
        user.setUsername("testUser");
        user.setPassword("testPassword12@");

        when(userRepository.findAllByEmail(user.getEmail())).thenReturn(new ArrayList<>());
        when(userRepository.findAllByUsername(user.getUsername())).thenReturn(new ArrayList<>());
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("testEncodedPassword");
        when(userRepository.saveAndFlush(user)).thenReturn(user);

        // Act
        User savedUser = userService.signUpUser(user);

        // Assert
        Assertions.assertEquals(user.getEmail(), savedUser.getEmail());
        Assertions.assertEquals(user.getUsername(), savedUser.getUsername());
        Assertions.assertEquals("testEncodedPassword", savedUser.getPassword());
    }

    @Test
    void testSignUpUserWithExistingEmail() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setUsername("testUser");
        user.setPassword("testPassword12@");
        user.setAdmin(false);
        user.setConfirmed(false);


        User user2 = new User();
        user2.setEmail("test@test.com");
        user2.setUsername("testUser");
        user2.setPassword("testPassword12@");
        user2.setConfirmed(true);
        user2.setAdmin(false);

        List<User> existingUsers = new ArrayList<>();
        existingUsers.add(user2);

        when(userRepository.findAllByEmail(user.getEmail())).thenReturn(existingUsers);

        // Act and assert
        Assertions.assertThrows(InvalidCredentialException.class, () -> userService.signUpUser(user));
    }

    @Test
    void testSignUpUserWithExistingUsername() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setUsername("testUser");
        user.setPassword("testPassword12@");
        user.setAdmin(false);
        user.setConfirmed(false);


        User user2 = new User();
        user2.setEmail("test@test.com");
        user2.setUsername("testUser");
        user2.setPassword("testPassword12@");
        user2.setConfirmed(true);
        user2.setAdmin(false);

        List<User> existingUsers = new ArrayList<>();
        existingUsers.add(user2);

        when(userRepository.findAllByUsername(user.getUsername())).thenReturn(existingUsers);

        Assertions.assertThrows(InvalidCredentialException.class, () -> userService.signUpUser(user));
    }
}