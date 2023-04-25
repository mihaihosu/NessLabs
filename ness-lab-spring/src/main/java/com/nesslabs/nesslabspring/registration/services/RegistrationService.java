package com.nesslabs.nesslabspring.registration.services;

import com.nesslabs.nesslabspring.registration.EmailValidator;
import com.nesslabs.nesslabspring.registration.models.RegistrationRequest;
import com.nesslabs.nesslabspring.user.models.User;
import com.nesslabs.nesslabspring.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private EmailValidator emailValidator;
    private final UserService userService;
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return userService.signUpUser(new User(
                request.getEmail(),
                request.getEmail(),
                request.getPassword(),
                true)
        );
    }
}
