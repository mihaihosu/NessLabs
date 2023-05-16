package com.nesslabs.nesslabspring.controllers;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.services.RegistrationService;
import com.nesslabs.nesslabspring.exception.InvalidConfirmationEmail;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    private final RegistrationService registrationService;

    @PostMapping("/auth/registration")
    public ResponseEntity<String> register (@RequestBody RegistrationRequest request) throws InvalidConfirmationEmail {
        try {
            registrationService.register(request);
        }catch (InvalidConfirmationEmail e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED). body("One more step.Please verify your account");
    }

    @GetMapping("/auth/registration/confirm")
    ResponseEntity<String> confirm(@RequestParam("token") String token) throws InvalidConfirmationEmail {
        try {
            registrationService.confirmToken(token);
         }catch(InvalidConfirmationEmail e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
         }
            return ResponseEntity.status(HttpStatus.CREATED).body("Account is verified successfully. Now you can login to your account");
        }

}