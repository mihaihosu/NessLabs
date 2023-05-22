package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;
import com.nesslabs.nesslabspring.exception.UnauthorizedException;

public interface EventValidator {
     void validate(EventDto eventDt, Long userId) throws InvalidInputException;

     void validateUserAuthorization(Long eventId, String token) throws UnauthorizedException;
}
