package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.entity.ConfirmationToken;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    ConfirmationToken getToken(String token);

    ConfirmationToken findTokenByUserId(int id);
}
