package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.PasswordResetRequest;

public interface PasswordResetService {
    void sendPasswordResetRequest(String email);

    void validatePasswordResetToken(String token);

    void resetPassword(String token, PasswordResetRequest request);
}
