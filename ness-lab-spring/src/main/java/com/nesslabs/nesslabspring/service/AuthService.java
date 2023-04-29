package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.AuthRequestDto;
import com.nesslabs.nesslabspring.dto.AuthResponseDto;

public interface AuthService {
   // AuthResponseDto authenticate(AuthRequestDto request);
   AuthResponseDto checkUserCredentials(AuthRequestDto loginRequestDto);
}
