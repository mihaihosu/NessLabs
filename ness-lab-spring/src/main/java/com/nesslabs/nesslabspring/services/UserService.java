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

    public int enableUser(String email) {
       return userRepository.enableUser(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user) {

        boolean mailExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();
        boolean usernameExists = userRepository.findByUsername(user.getUsername()).isPresent();

        if (mailExists) {
            User existUser = userRepository.findByEmail(user.getEmail()).orElse(null);
            if (!existUser.isEnabled()) {
                String encodedPassword = bCryptPasswordEncoder
                            .encode(user.getPassword());

                user.setPassword(encodedPassword);

                userRepository.updateUserDataByEmail(existUser.getEmail(), user.getPassword(), user.getUsername(), user.is_admin());

                confirmationTokenService.deleteToken(Math.toIntExact(existUser.getId()));

                String token = UUID.randomUUID().toString();

                ConfirmationToken confirmationToken = new ConfirmationToken(
                            token,
                            LocalDateTime.now(),
                            LocalDateTime.now().plusMinutes(60),
                            existUser
                    );

                confirmationTokenService.saveConfirmationToken(confirmationToken);

                 return token;

                } else {
                    return "EmailAccountConfirmed";
                }
        } else if( usernameExists ) {
            User existUser = userRepository.findByUsername(user.getUsername()).orElse(null);
            if (!existUser.isEnabled()) {
                String encodedPassword = bCryptPasswordEncoder
                        .encode(user.getPassword());

                user.setPassword(encodedPassword);

                userRepository.updateUserDataByUsername(existUser.getUsername(), user.getPassword(),user.getEmail(), user.is_admin());

                confirmationTokenService.deleteToken(Math.toIntExact(existUser.getId()));

                String token = UUID.randomUUID().toString();

                ConfirmationToken confirmationToken = new ConfirmationToken(
                        token,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(60),
                        existUser
                );

                confirmationTokenService.saveConfirmationToken(confirmationToken);

                return token;

            } else {
                return "UsernameAccountConfirmed";
            }
        } else{
            String token = UUID.randomUUID().toString();

            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(60),
                    user
            );

            String encodedPassword = bCryptPasswordEncoder
                    .encode(user.getPassword());

            user.setPassword(encodedPassword);

            userRepository.save(user);

            confirmationTokenService.saveConfirmationToken(confirmationToken);

            return token;
        }
    }
}
