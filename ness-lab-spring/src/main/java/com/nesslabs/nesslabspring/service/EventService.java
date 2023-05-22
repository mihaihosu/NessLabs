package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;
import com.nesslabs.nesslabspring.model.Event;

public interface EventService {

    Event updateEvent(Long eventId, EventDto eventDto, String token) throws InvalidInputException;

    Event createEvent(EventDto eventDto, String adminEmail);
}
