package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.entity.ConfirmationToken;
import com.nesslabs.nesslabspring.entity.User;
import com.nesslabs.nesslabspring.repositories.ConfirmationTokenRepository;
import com.nesslabs.nesslabspring.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConfirmationTokenServiceTest {

    @Mock
    private ConfirmationTokenServiceImpl confirmationTokenService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Test
    void ReturnTheConfirmationToken() {

        User user = new User("Mihai","mergeasergiu@gmail.com", "1234",false);
        user.setId(1L);
        ConfirmationToken confirmationToken = new ConfirmationToken("confirmationToken", LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);
        confirmationToken.setId(1L);

        userRepository.save(user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        ConfirmationToken token1 = confirmationTokenService.getToken("confirmationToken");

        assertNotNull(token1);

    }

//    @Test
//    void setConfirmedAt() {
//    }
//
//    @Test
//    void deleteToken() {
//    }
}