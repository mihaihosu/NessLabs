package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.entity.ConfirmationToken;
import com.nesslabs.nesslabspring.entity.User;
import com.nesslabs.nesslabspring.repositories.EmailSender;
import com.nesslabs.nesslabspring.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final ConfirmationTokenService confirmationTokenService;
    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailSender emailSender;

    public int enableUser(String email) {
       return userRepository.enableUser(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user) {

        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();


            if (userExists) {
                User existUser = userRepository.findByEmail(user.getEmail()).orElse(null);
                if (!existUser.isEnabled()) {

                    String encodedPassword = bCryptPasswordEncoder
                            .encode(user.getPassword());

                    user.setPassword(encodedPassword);

                    userRepository.updatePassword(existUser.getEmail(), user.getPassword());

                    userRepository.updateUsername(existUser.getEmail(), user.getUsername());

                    confirmationTokenService.deleteToken(Math.toIntExact(existUser.getId()));

                    String token = UUID.randomUUID().toString();
                    ConfirmationToken confirmationToken = new ConfirmationToken(
                            token,
                            LocalDateTime.now(),
                            LocalDateTime.now().plusMinutes(15),
                            existUser
                    );

                    confirmationTokenService.saveConfirmationToken(confirmationToken);

                    return token;
                } else {
                    return "null";
                }
            } else {
                String token = UUID.randomUUID().toString();
                ConfirmationToken confirmationToken = new ConfirmationToken(
                        token,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(15),
                        user
                );

                String encodedPassword = bCryptPasswordEncoder
                        .encode(user.getPassword());

                user.setPassword(encodedPassword);

                userRepository.save(user);

                confirmationTokenService.saveConfirmationToken(confirmationToken);

                //TODO: SEND EMAIL
                return token;
            }
        }
}
