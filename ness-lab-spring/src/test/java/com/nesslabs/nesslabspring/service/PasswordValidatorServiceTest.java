package com.nesslabs.nesslabspring.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorServiceTest {

    @Test
    void testValidPassword() {
        String password = "MyPassword123$%^";
        assertTrue(new PasswordValidatorService().test(password));
    }

    @Test
    void testNullPassword() {
        assertFalse(new PasswordValidatorService().test(null));
    }

    @Test
    void testEmptyPassword() {
        assertFalse(new PasswordValidatorService().test(""));
    }

    @Test
    void testTooShortPassword() {
        String password = "passwor";
        assertFalse(new PasswordValidatorService().test(password));
    }

    @Test
    void testTooLongPassword() {
        String password = "thispasswordistoolongthispasswordistoolongthispasswordistoolongthispasswordistoolong1";
        assertFalse(new PasswordValidatorService().test(password));
    }

    @Test
    void testNoLowerCaseLetter() {
        String password = "MYPASSWORD123$%^";
        assertFalse(new PasswordValidatorService().test(password));
    }

    @Test
    void testNoUpperCaseLetter() {
        String password = "mypassword123$%^";
        assertFalse(new PasswordValidatorService().test(password));
    }

    @Test
    void testNoDigit() {
        String password = "MyPassword$%^";
        assertFalse(new PasswordValidatorService().test(password));
    }

    @Test
    void testValidPasswordWithoutSpecialCharacters() {
        String password = "MyPassword123";
        assertTrue(new PasswordValidatorService().test(password));
    }
}