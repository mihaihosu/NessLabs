package com.nesslabs.nesslabspring.service;

import static org.junit.jupiter.api.Assertions.*;

import com.nesslabs.nesslabspring.dto.AuthRequestDto;
import com.nesslabs.nesslabspring.dto.AuthResponseDto;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenEmailAndPasswordAreCorrect_thenReturnAuthResponseDto() {
        // Arrange
        String email = "alexiaoaida@gmail.com";
        String password = "pisica";
        AuthRequestDto authRequestDto = new AuthRequestDto(email, password);
        User user = new User(email, "alexiaoaida", password, true,true);
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);

        // Act
        AuthResponseDto authResponseDto = authService.checkUserCredentials(authRequestDto);

        // Assert
        assertNotNull(authResponseDto);
        assertEquals(email, authResponseDto.getEmail());
        assertTrue(authResponseDto.getIsAdmin());
    }

    @Test
    void whenEmailIsCorrectAndPasswordIsWrong_thenReturnNull() {
        // Arrange
        String email = "alexiaoaida@gmail.com";
        String password = "wrong_password";
        AuthRequestDto authRequestDto = new AuthRequestDto(email, password);
        User user = new User(email, "alexiaoaida", "correct_password", true,true);
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(false);

        // Act
        AuthResponseDto authResponseDto = authService.checkUserCredentials(authRequestDto);

        // Assert
        assertNull(authResponseDto);
    }

    @Test
    void whenEmailIsWrong_thenReturnNull() {
        // Arrange
        String email = "wrong_email@gmail.com";
        String password = "password";
        AuthRequestDto authRequestDto = new AuthRequestDto(email, password);
        when(userRepository.findByEmail(email)).thenReturn(null);

        // Act
        AuthResponseDto authResponseDto = authService.checkUserCredentials(authRequestDto);

        // Assert
        assertNull(authResponseDto);
    }

    @Test
    void whenAuthRequestDtoIsNull_thenThrowNullPointerException() {
        // Arrange
        AuthRequestDto authRequestDto = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> authService.checkUserCredentials(authRequestDto));
    }

    @Test
    void whenEmailIsNullAndPasswordIsNull_thenReturnNull() {
        // Arrange
        AuthRequestDto authRequestDto = new AuthRequestDto(null, null);

        // Act
        AuthResponseDto authResponseDto = authService.checkUserCredentials(authRequestDto);

        // Assert
        assertNull(authResponseDto);
    }

    @Test
    void whenEmailIsEmptyAndPasswordIsEmpty_thenReturnNull() {
        // Arrange
        AuthRequestDto authRequestDto = new AuthRequestDto("", "");

        // Act
        AuthResponseDto authResponseDto = authService.checkUserCredentials(authRequestDto);

        // Assert
        assertNull(authResponseDto);
    }

}
