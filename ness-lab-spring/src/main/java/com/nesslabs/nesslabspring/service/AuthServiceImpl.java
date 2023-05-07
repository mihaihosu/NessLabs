package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.AuthRequestDto;
import com.nesslabs.nesslabspring.dto.AuthResponseDto;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repository.UserRepository;
import com.nesslabs.nesslabspring.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtTokenService;


    //FOR DB
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    //WHEN I HAVE USERS IN DB
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
            responseDto.setIsAdmin(userEntity.getIs_admin());
            return responseDto;
        }
        return null;
    }

        public String createToken(AuthResponseDto loginResponseDto) {
            User user = getUserByEmail(loginResponseDto.getEmail());

            if (!user.getIs_confirmed()) {
                return null; // user's account is not confirmed, return null
            }

            Boolean isAdmin = loginResponseDto.getIsAdmin();
            String jwt = jwtTokenService.generateToken(loginResponseDto.getEmail(), isAdmin);
            return jwt;
        }


        public HttpHeaders createHeader(String token) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            return headers;
        }

/*
        //FOR HARDCODED LIST
        @Override
        public User getUserByEmail(String email) {
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
            return null; // user not found, return null
        }

        // add a hardcoded test user
        private final List<User> users = Arrays.asList(
                new User("alexiaoaida@gmail.com", "alexiaoaida", "pisica", true, true));

        //TEST WITH LIST
        public AuthResponseDto checkUserCredentials(AuthRequestDto loginRequestDto) {
            for (User user : users) {
                if (user.getEmail().equals(loginRequestDto.getEmail()) &&
                        user.getPassword().equals(loginRequestDto.getPassword())) {
                    AuthResponseDto responseDto = new AuthResponseDto();
                    responseDto.setEmail(user.getEmail());
                    responseDto.setIsAdmin(user.getIs_admin());
                    return responseDto;
                }
            }
            return null;
        }

*/

}
