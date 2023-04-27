package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.model.PasswordResetToken;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repository.PasswordResetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Calendar;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetRepository passwordResetRepository;

    public void createPasswordResetTokenForUser(User user, String passwordToken) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(passwordToken, user);
        passwordResetRepository.save(passwordResetToken);
    }

    public String validatePasswordResetToken(String theToken) {
        PasswordResetToken token = passwordResetRepository.findByToken(theToken);
        if(token==null) {
            return "Invalid verification token";
        }

        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if((token.getExpirationTime().getTime()-calendar.getTime().getTime()) <= 0 ) {
            return "Verification link has expired\n"+
                    "Click the link below to receive a new verification link.";
        }
        return "valid";
    }

    public Optional<User> findUserByPasswordToken(String passwordToken) {
       return Optional.ofNullable(passwordResetRepository.findByToken(passwordToken).getUser());
    }

}
