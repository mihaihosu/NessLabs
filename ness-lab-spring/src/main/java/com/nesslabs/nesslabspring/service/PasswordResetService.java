package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.exception.InvalidCredentialException;
import com.nesslabs.nesslabspring.exception.InvalidTokenException;
import com.nesslabs.nesslabspring.model.PasswordResetToken;
import com.nesslabs.nesslabspring.model.User;
import com.nesslabs.nesslabspring.repository.PasswordResetRepository;
import com.nesslabs.nesslabspring.repository.UserRepository;
import com.nesslabs.nesslabspring.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;


@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetRepository passwordResetRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PasswordValidatorService passwordValidatorService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void sendPasswordResetRequest(String email) throws InvalidCredentialException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new InvalidCredentialException("Email has been sent successfully");
        }
        String token = jwtService.generateToken(user);
        createPasswordResetTokenForUser(user,token);
        String link = "http://localhost:8080/api/v1/pwdres/receive?token=" + token;
        emailService.send(email,buildEmail(user.getEmail(),link));
    }

    public void createPasswordResetTokenForUser(User user, String passwordToken) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(passwordToken, user);
        passwordResetRepository.save(passwordResetToken);
    }

    public void resetPassword(String token, String newPassword) throws InvalidTokenException, InvalidCredentialException {
        try{
            validatePasswordResetToken(token);
        }catch (InvalidTokenException e) {
            throw new InvalidTokenException(e.getMessage());
        }
        try {
            validateNewPassword(newPassword);
        }catch (InvalidCredentialException e) {
            throw new InvalidCredentialException(e.getMessage());
        }
        PasswordResetToken passwordResetToken = passwordResetRepository.findByToken(token);
        User user = passwordResetToken.getUser();
        changeUserPassword(user,newPassword);
        passwordResetToken.setUsed(true);
        passwordResetRepository.save(passwordResetToken);
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void validateNewPassword(String newPassword) {
        boolean isValidPassword = passwordValidatorService.test(newPassword);
        if(!isValidPassword) {
            throw new InvalidCredentialException("Password not valid, ensure at least one lowercase letter," +
                    " one uppercase letter, one digit, at least 8 characters, and a special character");
        }
    }

    public void validatePasswordResetToken(String token) throws InvalidTokenException{
        final PasswordResetToken passToken = passwordResetRepository.findByToken(token);
        if(isTokenFound(passToken)) {
            if (!isTokenExpired(passToken)) {
                if(isTokenUsed(passToken)) {
                    throw new InvalidTokenException("Token is invalid or has expired");
                }
            } else throw new InvalidTokenException("Token is invalid or has expired");
        } else throw new InvalidTokenException("Token is invalid or has expired");
    }

    private boolean isTokenUsed(PasswordResetToken passToken) {
        return passToken.isUsed();
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpirationTime().before(cal.getTime());
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
