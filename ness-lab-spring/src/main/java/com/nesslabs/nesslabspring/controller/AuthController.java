package com.nesslabs.nesslabspring.controller;


import com.nesslabs.nesslabspring.dto.AuthRequestDto;
import com.nesslabs.nesslabspring.dto.AuthResponseDto;
import com.nesslabs.nesslabspring.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    private ResponseEntity<AuthRequestDto> loginUser(@RequestBody AuthRequestDto loginRequestDto) {
        AuthResponseDto loginResponseDto = authService.checkUserCredentials(loginRequestDto);

        if (loginResponseDto != null) {
            String token = authService.createToken(loginResponseDto);
            if (token != null) {

                HttpHeaders headers = authService.createHeader(token);
                return ResponseEntity.ok().headers(headers).body(null);

            } else {
                return ResponseEntity.status(401).build();
            }
        }
        return ResponseEntity.status(401).build();
    }




}
