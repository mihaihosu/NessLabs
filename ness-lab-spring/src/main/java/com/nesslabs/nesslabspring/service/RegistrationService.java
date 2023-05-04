package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private EmailValidatorService emailValidatorService;
    private PasswordValidatorService passwordValidatorService;
    private final UserService userService;

    public User register(RegistrationRequest request) {
        boolean isValidEmail = emailValidatorService.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        boolean isValidPassword = passwordValidatorService.test(request.getPassword());
        if(!isValidPassword) {
            throw new IllegalStateException("password not valid, ensure at least one lowercase letter, uppercase letter and one digit, at least 8 characters");
        }

        if (request.getUsername().isEmpty() || request.getUsername() == null) {
            throw new IllegalStateException("username not valid");
        }


        return userService.signUpUser(
                User.builder()
                        .username(request.getUsername())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .is_admin(request.is_admin())
                        .build()
        );
    }
}
