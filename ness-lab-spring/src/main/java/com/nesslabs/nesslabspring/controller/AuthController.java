package com.nesslabs.nesslabspring.controller;


import com.nesslabs.nesslabspring.dto.AuthRequestDto;
import com.nesslabs.nesslabspring.dto.AuthResponseDto;
import com.nesslabs.nesslabspring.dto.TokenDto;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.security.JwtService;
import com.nesslabs.nesslabspring.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    private final JwtService jwtTokenService;

    @PostMapping("/auth/login")
    private ResponseEntity<TokenDto> loginUser(@RequestBody AuthRequestDto loginRequestDto) {
        AuthResponseDto loginResponseDto = authService.checkUserCredentials(loginRequestDto);

        if (loginResponseDto != null) {
            TokenDto tokenDto = authService.createToken(loginResponseDto);

            if (tokenDto != null) {
                String token = tokenDto.getToken();
                HttpHeaders headers = authService.createHeader(token);
                return ResponseEntity.ok().headers(headers).body(tokenDto);

            } else {
                return ResponseEntity.status(401).build();
            }
        }
        return ResponseEntity.status(401).build();
    }





}
