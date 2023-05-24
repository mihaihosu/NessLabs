package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.exception.InvalidCredentialException;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService{

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserDetails> userDetailsOptional = Optional.ofNullable(userRepository.findByEmail(email));
        return userDetailsOptional
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
        System.out.println(user);
        return user;
    }

    //FUNCTIONALITY WILL WORK WITH SERGIUS' MERGE
    private boolean isUserByEmailConfirmed(String email) {
        List<User> users = userRepository.findAllByEmail(email);
        return users.stream()
                .anyMatch(User::isConfirmed);
    }

    private boolean isUserByUsernameConfirmed(String username) {
        List<User> users = userRepository.findAllByUsername(username);
        return users.stream()
                .anyMatch(User::isConfirmed);
    }

}
