package com.nesslabs.nesslabspring.controller;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping("/auth/registration")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

}