package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.entity.ConfirmationToken;
import com.nesslabs.nesslabspring.entity.User;
import com.nesslabs.nesslabspring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ConfirmationTokenService confirmationTokenService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public void enableUser(String email) {
        userRepository.enableUser(email);
    }

    public String signUpUser(User user) {

        boolean mailExists = findByEmail(user.getEmail())
                .isPresent();
        boolean usernameExists = findByUsername(user.getUsername()).isPresent();

        if (mailExists) {
            User existUser = findByEmail(user.getEmail()).orElse(null);
            if (!existUser.isEnabled()) {
                String encodedPassword = bCryptPasswordEncoder
                            .encode(user.getPassword());

                existUser.setUsername(user.getUsername());
                existUser.setPassword(encodedPassword);
                existUser.set_admin(user.is_admin());

                userRepository.save(existUser);

                String token = UUID.randomUUID().toString();

                ConfirmationToken foundConfirmationToken = confirmationTokenService.findTokenByUserId(Math.toIntExact(existUser.getId()));

                foundConfirmationToken.setToken(token);
                foundConfirmationToken.setConfirmedAt(null);
                foundConfirmationToken.setCreatedAt(LocalDateTime.now());
                foundConfirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(60));
                foundConfirmationToken.setUser(existUser);

                confirmationTokenService.saveConfirmationToken(foundConfirmationToken);

                 return token;

                } else {
                    return "EmailAccountConfirmed";
                }
        } else if( usernameExists ) {
            User existUser = findByUsername(user.getUsername()).orElse(null);
            if (!existUser.isEnabled()) {
                String encodedPassword = bCryptPasswordEncoder
                        .encode(user.getPassword());

                existUser.setEmail(user.getEmail());
                existUser.setPassword(encodedPassword);
                existUser.set_admin(user.is_admin());

                userRepository.save(existUser);

                String token = UUID.randomUUID().toString();

                ConfirmationToken foundConfirmationToken = confirmationTokenService.findTokenByUserId(Math.toIntExact(existUser.getId()));

                foundConfirmationToken.setToken(token);
                foundConfirmationToken.setConfirmedAt(null);
                foundConfirmationToken.setCreatedAt(LocalDateTime.now());
                foundConfirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(60));
                foundConfirmationToken.setUser(existUser);

                confirmationTokenService.saveConfirmationToken(foundConfirmationToken);

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
