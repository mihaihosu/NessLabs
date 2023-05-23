package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;

public interface EventValidator {
     void validate(EventDto eventDto) throws InvalidInputException;
}
