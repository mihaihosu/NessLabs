package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.EventNotFoundException;
import com.nesslabs.nesslabspring.mappers.EventMapper;
import com.nesslabs.nesslabspring.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    @Override
    public List<EventDto> getEventsWithPaginationAndFiltered(
            LocalDate startDate, LocalDate endDate, String tags, String characteristics,
            Integer isFree, Integer eventTypes, String searchInput, Integer myEvents,
            Integer pageNumber, Integer pageSize
    ) {
        return null;
    }

    @Override
    public EventDto getEventById(Long id) throws EventNotFoundException {
        return eventRepository.findById(id).map(eventMapper::toDto)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));
    }
}
