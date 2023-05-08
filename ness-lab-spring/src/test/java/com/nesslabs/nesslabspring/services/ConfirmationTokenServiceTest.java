package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.entity.ConfirmationToken;
import com.nesslabs.nesslabspring.entity.User;
import com.nesslabs.nesslabspring.repositories.ConfirmationTokenRepository;
import com.nesslabs.nesslabspring.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfirmationTokenServiceTest {

    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;


//    @Test
//    void ReturnTheConfirmationToken() {
//
//        User user = User.builder()
//                .id(1L)
//                .email("example@yahoo.com")
//                .username("example")
//                .password("Password1233")
//                .is_admin(false)
//                .build();
//
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
//
//        String token = userService.signUpUser(user);
//
//        ConfirmationToken tokenFound = confirmationTokenService.getToken(token);
//
//        assertEquals(tokenFound.getToken(),"confirmedToken");
//        assertNotEquals(tokenFound.getCreatedAt(), null);
//
//    }

//    @Test
//    void setConfirmedAt() {
//    }
//
//    @Test
//    void deleteToken() {
//    }
}