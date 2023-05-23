package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.RegistrationDto;
import com.nesslabs.nesslabspring.model.User;

public interface RegistrationService {
    User register(RegistrationDto request);
}
