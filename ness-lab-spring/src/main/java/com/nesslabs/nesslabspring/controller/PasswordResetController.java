package com.nesslabs.nesslabspring.controller;

import com.nesslabs.nesslabspring.dto.PasswordResetRequest;
import com.nesslabs.nesslabspring.dto.SendPasswordResetEmailRequest;
import com.nesslabs.nesslabspring.services.PasswordResetService;
import com.nesslabs.nesslabspring.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
@CrossOrigin
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @PostMapping("/pwdres/request")
    public String sendPasswordResetRequest(@RequestBody SendPasswordResetEmailRequest request) {
        return passwordResetService.sendPasswordResetRequest(request);
    }

    @PostMapping("/pwdres/receive")
    public String postMethod(@RequestParam("token") String token) {
        return passwordResetService.validatePasswordResetToken(token);
    }

    @PostMapping("/pwdres/reset")
    public String resetPassword(@RequestBody PasswordResetRequest request) {
        return passwordResetService.resetPassword(request);
    }

}
