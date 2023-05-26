package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.EventNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<EventDto> getEventsWithPaginationAndFiltered(
            LocalDateTime startDate, LocalDateTime endDate, String tags, String characteristics,
            Boolean isFree, String eventStatus, String searchInput, Integer myEvents,
            Integer offset, Integer pageSize
    );

    EventDto getEventById(Long id) throws EventNotFoundException;
}
