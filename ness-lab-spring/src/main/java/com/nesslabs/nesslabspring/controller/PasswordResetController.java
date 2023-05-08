package com.nesslabs.nesslabspring.controller;

import com.nesslabs.nesslabspring.exception.InvalidCredentialException;
import com.nesslabs.nesslabspring.exception.InvalidTokenException;
import com.nesslabs.nesslabspring.service.PasswordResetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
@CrossOrigin
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @PostMapping("/pwdres/request")
    public ResponseEntity<String> sendPasswordResetRequest(@RequestHeader String email) {
        try{
            passwordResetService.sendPasswordResetRequest(email);
        } catch (InvalidCredentialException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Email has been sent successfully");
    }

    @PostMapping("/pwdres/receive")
    public ResponseEntity<String> receivePasswordResetRequest(@RequestParam("token") String token) {
        try {
            passwordResetService.validatePasswordResetToken(token);
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Password reset token validated");
    }

    @PostMapping("/pwdres/reset")
    public ResponseEntity<String> resetPassword(@RequestHeader String token, @RequestHeader String newPassword) {
        try {
            passwordResetService.resetPassword(token, newPassword);
        } catch (InvalidTokenException | InvalidCredentialException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Password has been reset");
    }

}
