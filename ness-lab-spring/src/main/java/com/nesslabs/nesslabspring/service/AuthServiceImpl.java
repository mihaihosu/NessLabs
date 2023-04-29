package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.AuthRequestDto;
import com.nesslabs.nesslabspring.dto.AuthResponseDto;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repository.UserRepository;
import com.nesslabs.nesslabspring.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    /*public AuthResponseDto authenticate(AuthRequestDto request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getEmail())
                .orElseThrow();
        boolean isAdmin = user.getIsAdmin();
        var jwtToken = jwtService.generateToken(isAdmin, user);

        return AuthResponseDto.builder()
                .token(jwtToken)
                .build();


    }*/

    // add a hardcoded test user
    private final List<User> users = Arrays.asList(
            new User("alexiaoaida@gmail.com", "alexiaoaida", "pisica", true));



    //WHEN I HAVE USERS IN DB

    /*public AuthResponseDto checkUserCredentials(AuthRequestDto loginRequestDto) {
        User userEntity = userRepository.findByEmail(loginRequestDto.getEmail());

        if (userEntity != null && passwordEncoder.matches(loginRequestDto.getPassword(),userEntity.getPassword())) {
            AuthResponseDto responseDto = new AuthResponseDto();
            responseDto.setEmail(userEntity.getEmail());
            responseDto.setIsAdmin(userEntity.getIs_admin());
            return responseDto;
        }
        return null;
    }*/

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




}
