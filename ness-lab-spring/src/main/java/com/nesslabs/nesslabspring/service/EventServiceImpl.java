package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;

import java.time.LocalDate;
import java.util.List;

public class EventServiceImpl implements EventService{
    @Override
    public List<EventDto> getEventsWithPaginationAndFiltered(
            LocalDate startDate, LocalDate endDate, String tags, String characteristics,
            Integer isFree, Integer eventTypes, String searchInput, Integer myEvents,
            Integer pageNumber, Integer pageSize
    ) {
        return null;
    }
}
