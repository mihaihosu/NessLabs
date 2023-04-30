package com.nesslabs.nesslabspring.controllers;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.entity.ConfirmationToken;
import com.nesslabs.nesslabspring.entity.User;
import com.nesslabs.nesslabspring.repositories.ConfirmationTokenRepository;
import com.nesslabs.nesslabspring.services.RegistrationService;
import com.nesslabs.nesslabspring.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final ConfirmationTokenRepository tokenRepository;
    private final UserService userService;

    @PostMapping("/auth/registration")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }


    @GetMapping("/auth/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        String verificationResult = registrationService.confirmToken(token);
        if (verificationResult.equalsIgnoreCase("confirmed")) {
            return "Email is verifoed successfully. Now you can login to your account";
        } else {
            return "Invalid verfication token";
        }
    }

}