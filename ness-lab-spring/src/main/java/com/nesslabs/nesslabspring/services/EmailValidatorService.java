package com.nesslabs.nesslabspring.services;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidatorService implements Predicate<String> {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";

    @Override
    public boolean test(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        return email.matches(EMAIL_REGEX);
    }
}
