package com.nesslabs.nesslabspring.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorServiceTest {

    private final EmailValidator emailValidatorService = new EmailValidator();

    @Test
    public void testValidEmail() {
        String validEmail = "example@example.com";
        boolean result = emailValidatorService.test(validEmail);
        assertTrue(result);
    }

    @Test
    public void testInvalidEmail() {
        String invalidEmail = "example.com";
        boolean result = emailValidatorService.test(invalidEmail);
        assertFalse(result);
    }

    @Test
    public void testNullEmail() {
        String nullEmail = null;
        boolean result = emailValidatorService.test(nullEmail);
        assertFalse(result);
    }

    @Test
    public void testEmptyEmail() {
        String emptyEmail = "";
        boolean result = emailValidatorService.test(emptyEmail);
        assertFalse(result);
    }
}
