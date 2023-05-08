package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.exception.InvalidCredentialException;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public User signUpUser(User user) throws InvalidCredentialException {
        boolean userExistsByEmail = isUserByEmailConfirmed(user.getEmail());
        boolean userExistsByUsername = isUserByUsernameConfirmed(user.getUsername());

        if(userExistsByEmail) {
            throw new InvalidCredentialException("Email already taken");
        }

        if(userExistsByUsername) {
            throw new InvalidCredentialException("Username already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.saveAndFlush(user);

        return user;
    }

    private boolean isUserByEmailConfirmed(String email) {
        List<User> users = userRepository.findAllByEmail(email);
        return users.stream()
                .anyMatch(User::is_confirmed);
    }

    private boolean isUserByUsernameConfirmed(String username) {
        List<User> users = userRepository.findAllByUsername(username);
        return users.stream()
                .anyMatch(User::is_confirmed);
    }

}
