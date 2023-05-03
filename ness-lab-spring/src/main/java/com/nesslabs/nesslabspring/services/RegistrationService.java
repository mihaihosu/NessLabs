package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
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
    private final UserService userService;
    private final UserRepository userRepository;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidatorService.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
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
