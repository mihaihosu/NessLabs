package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.RegistrationRequest;
import com.nesslabs.nesslabspring.model.User;

public interface RegistrationService {
    User register(RegistrationRequest request);
}
