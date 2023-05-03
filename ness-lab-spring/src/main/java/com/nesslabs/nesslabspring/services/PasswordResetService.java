package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.dto.PasswordResetRequest;
import com.nesslabs.nesslabspring.dto.SendPasswordResetEmailRequest;
import com.nesslabs.nesslabspring.model.PasswordResetToken;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repositories.PasswordResetRepository;
import com.nesslabs.nesslabspring.repositories.UserRepository;
import com.nesslabs.nesslabspring.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetRepository passwordResetRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;

    public String sendPasswordResetRequest(SendPasswordResetEmailRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if(user.isEmpty()) {
            return "invalid email";  //just for debugging purposes, will return same message as success scenario
        }
        String token = jwtService.generateToken(user.get());
//        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(user.get(),token);
        String link = "http://localhost:8080/api/v1/pwdres/receive?token=" + token;
        emailService.send(request.getEmail(),buildEmail(user.get().getEmail(),link));
        return "it works";
    }

    public void createPasswordResetTokenForUser(User user, String passwordToken) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(passwordToken, user);
        passwordResetRepository.save(passwordResetToken);
    }

    public String resetPassword(PasswordResetRequest request) {
        String token = request.getToken();
        if(!Objects.equals(validatePasswordResetToken(token), "confirmed")) {
            return validatePasswordResetToken(token);
        }
        User user = passwordResetRepository.findByToken(token).get().getUser();
        changeUserPassword(user,request.getNewPassword());
        return "it works";
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }


    public String validatePasswordResetToken(String token) {
        final Optional<PasswordResetToken> passToken = passwordResetRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : "confirmed";
    }

    private boolean isTokenFound(Optional<PasswordResetToken> passToken) {
        return passToken.isPresent();
    }

    private boolean isTokenExpired(Optional<PasswordResetToken> passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.map(passwordResetToken -> passwordResetToken
                .getExpirationTime().before(cal.getTime()))
                .orElse(true);
    }


    public String buildEmail(String name, String link) {
        return "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Reset your password</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <p>Dear "+ name +",</p>\n" +
                "    <p>We received a request to reset your password. To reset your password, click the button below:</p>\n" +
                "    <p>\n" +
                "      <a href=\""+link+"\" target=\"_blank\" style=\"display:inline-block;background-color:#3c8dbc;color:#fff;font-size:16px;padding:10px 20px;text-decoration:none;border-radius:5px;\">Reset Password</a>\n" +
                "    </p>\n" +
                "    <p>If you did not request a password reset, please ignore this email.</p>\n" +
                "    <p>Best regards,</p>\n" +
                "    <p>[your company name]</p>\n" +
                "  </body>\n" +
                "</html>";
    }

}
