package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private final PasswordResetService passwordResetService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user){
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if(userExists) {
            throw new IllegalStateException("Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return "it works";
    }

//    public User findUserByEmail(String email) {
//        return new User();
//    }

//    public String changeUserPassword(User user, String password) {
//        String encodedPassword = bCryptPasswordEncoder.encode(password);
//        user.setPassword(encodedPassword);
//        userRepository.save(user);
//        return "password reset works";
//    }
//
//    public User findByEmail(String email) {
//        return new User();
//    }
//
//    public void createPasswordResetTokenForUser(User user, String passwordToken) {
//        passwordResetService.createPasswordResetTokenForUser(user,passwordToken);
//    }
//
//    public String validatePasswordResetToken(String passwordResetToken) {
//        return passwordResetService.validatePasswordResetToken(passwordResetToken);
//    }
//
//    public User findUserByPasswordToken(String passwordResetToken) {
//        return passwordResetService.findUserByPasswordToken(passwordResetToken).get();
//    }
//
//    public void resetUserPassword(User user, String newPassword) {
//        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
//        userRepository.save(user);
//    }
}
