package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;

public interface EventService {
    void createEvent(EventDto eventDto, String token) throws InvalidInputException;
}
