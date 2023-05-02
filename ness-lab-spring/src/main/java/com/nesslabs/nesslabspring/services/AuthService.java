package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.AuthRequestDto;
import com.nesslabs.nesslabspring.dto.AuthResponseDto;
import com.nesslabs.nesslabspring.model.User;

public interface AuthService {
   User getUserByEmail(String email);
   AuthResponseDto checkUserCredentials(AuthRequestDto loginRequestDto);
}
