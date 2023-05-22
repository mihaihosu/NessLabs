package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.AuthRequestDto;
import com.nesslabs.nesslabspring.dto.AuthResponseDto;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repository.UserRepository;
import com.nesslabs.nesslabspring.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtTokenService;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public AuthResponseDto checkUserCredentials(AuthRequestDto loginRequestDto) {

        if (loginRequestDto.getEmail() == null || loginRequestDto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email field is required");
        }
        if (loginRequestDto.getPassword() == null || loginRequestDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password field is required");
        }


        User userEntity = userRepository.findByEmail(loginRequestDto.getEmail());

        if (userEntity != null && passwordEncoder.matches(loginRequestDto.getPassword(),userEntity.getPassword())) {
            AuthResponseDto responseDto = new AuthResponseDto();
            responseDto.setEmail(userEntity.getEmail());
            responseDto.setIsAdmin(userEntity.is_admin());
            return responseDto;
        }
        return null;
    }

        @Override
        public String createToken(AuthResponseDto loginResponseDto) {
            User user = getUserByEmail(loginResponseDto.getEmail());

            /*if (!user.is_confirmed()) {
                return null;
            }*/

            Boolean isAdmin = loginResponseDto.getIsAdmin();
            String jwt = jwtTokenService.generateToken(loginResponseDto.getEmail(), isAdmin);
            return jwt;
        }

        @Override
        public HttpHeaders createHeader(String token) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            return headers;
        }

}
