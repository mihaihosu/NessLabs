package com.nesslabs.nesslabspring.services;


import com.nesslabs.nesslabspring.entity.ConfirmationToken;
import com.nesslabs.nesslabspring.entity.User;
import com.nesslabs.nesslabspring.repositories.ConfirmationTokenRepository;
import com.nesslabs.nesslabspring.repositories.UserRepository;
import com.nesslabs.nesslabspring.exception.InvalidConfirmationEmail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Captor
    private ArgumentCaptor<ConfirmationToken> tokenCaptor;



    @Test
    public void testSignedUpUserCreateConfirmationToken() throws InvalidConfirmationEmail{

        User user = User.builder()
                .id(1L)
                .email("example@yahoo.com")
                .username("example")
                .password("Password1233")
                .is_admin(false)
                .build();

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("hashedpassword");

        String confirmationToken = userService.signUpUser(user);

        assertNotEquals(confirmationToken,"null");

        verify(userRepository, times(1)).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertEquals(user.getEmail(), capturedUser.getEmail());
        assertEquals("hashedpassword", capturedUser.getPassword());


    }

    @Test
    public void testSignedUpUserReturnNullTokenWhenUserAlready() throws InvalidConfirmationEmail{

        User user = User.builder().id(1L)
                .username("example")
                .email("example@yahoo.com")
                .password("Password1233")
                .is_admin(false)
                .build();

        user.setIs_confirmed(true);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User user2 = User.builder().id(2L)
                .username("example")
                .email("example@yahoo.com")
                .password("Password1233")
                .is_admin(false)
                .build();

        String confirmationToken = userService.signUpUser(user2);

        assertEquals(confirmationToken, "null");

    }


}
