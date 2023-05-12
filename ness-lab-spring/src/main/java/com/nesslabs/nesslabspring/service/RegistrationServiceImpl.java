package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.exception.InvalidCredentialException;
import com.nesslabs.nesslabspring.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{
    private EmailValidatorService emailValidatorService;
    private PasswordValidatorService passwordValidatorService;
    private final UserServiceImpl userService;

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