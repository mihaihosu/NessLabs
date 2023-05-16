package com.nesslabs.nesslabspring.services;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.exception.InvalidConfirmationEmail;

public interface RegistrationService {

    void register(RegistrationRequest request) throws InvalidConfirmationEmail;

    void confirmToken(String token) throws InvalidConfirmationEmail;

    String buildEmail(String name, String link) ;

}
