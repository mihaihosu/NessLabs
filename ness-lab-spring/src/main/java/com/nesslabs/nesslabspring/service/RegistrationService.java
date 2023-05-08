package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.exception.InvalidCredentialException;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.desktop.OpenFilesEvent;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {
    private EmailValidatorService emailValidatorService;
    private PasswordValidatorService passwordValidatorService;
    private final UserService userService;
    private final UserRepository userRepository;

    public User register(RegistrationRequest request) throws InvalidCredentialException {
        boolean isValidEmail = emailValidatorService.test(request.getEmail());
        if (!isValidEmail) {
            throw new InvalidCredentialException("email not valid");
        }
        boolean isValidPassword = passwordValidatorService.test(request.getPassword());
        if(!isValidPassword) {
            throw new InvalidCredentialException("password not valid, ensure at least one lowercase letter, uppercase letter and one digit, at least 8 characters");
        }

        if (request.getUsername().isEmpty() || request.getUsername() == null) {
            throw new InvalidCredentialException("Username not valid");
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
