package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private EmailValidatorService emailValidatorService;
    private final UserService userService;

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
