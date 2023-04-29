package com.nesslabs.nesslabspring.controller;


import com.nesslabs.nesslabspring.dto.AuthRequestDto;
import com.nesslabs.nesslabspring.dto.AuthResponseDto;
import com.nesslabs.nesslabspring.dto.TokenDto;
import com.nesslabs.nesslabspring.security.JwtService;
import com.nesslabs.nesslabspring.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
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
            Boolean isAdmin = loginResponseDto.getIsAdmin();
            String jwt = jwtTokenService.generateToken(loginResponseDto.getEmail(), isAdmin);
            TokenDto token = new TokenDto();
            token.setToken(jwt);
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(401).build();
    }


}
