package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.EventNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.nesslabs.nesslabspring.exception.InvalidInputException;
import com.nesslabs.nesslabspring.model.Event;

public interface EventService {

    Event updateEvent(Long eventId, EventDto eventDto, String token) throws InvalidInputException;

    List<EventDto> getEventsWithPaginationAndFiltered(
            LocalDateTime startDate, LocalDateTime endDate, String tags, String characteristics,
            Boolean isFree, String eventStatus, String searchInput, Boolean myEvents,
            Integer offset, Integer pageSize, String token
    );

    EventDto getEventById(Long id) throws EventNotFoundException;
}
