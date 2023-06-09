package com.nesslabs.nesslabspring.controller;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.exception.InvalidCredentialException;
import com.nesslabs.nesslabspring.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/auth/registration")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        try {
            registrationService.register(request);
        }catch (InvalidCredentialException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }
}
