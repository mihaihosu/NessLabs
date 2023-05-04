package com.nesslabs.nesslabspring.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class PasswordValidatorService implements Predicate<String> {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 64;
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,64}$";
    @Override
    public boolean test(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        if (password.length() < MIN_LENGTH || password.length() > MAX_LENGTH) {
            return false;
        }

        return password.matches(PASSWORD_REGEX);
    }
}
