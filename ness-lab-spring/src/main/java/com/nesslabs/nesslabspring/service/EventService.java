package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;
import com.nesslabs.nesslabspring.model.Event;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;

public interface EventService {

    Event updateEvent(Long eventId, EventDto eventDto, String token) throws InvalidInputException;
    void createEvent(EventDto eventDto, String token) throws InvalidInputException;
}
