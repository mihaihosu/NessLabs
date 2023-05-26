package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.enums.EventStatus;
import com.nesslabs.nesslabspring.exception.EventNotFoundException;
import com.nesslabs.nesslabspring.mappers.EventMapper;
import com.nesslabs.nesslabspring.model.Event;
import com.nesslabs.nesslabspring.repository.EventRepository;
import com.nesslabs.nesslabspring.utils.QueryParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    @Override
    public List<EventDto> getEventsWithPaginationAndFiltered(
            LocalDateTime startDate, LocalDateTime endDate, String tags, String characteristics,
            Boolean isFree, String eventStatus, String searchInput, Integer myEvents,
            Integer offset, Integer pageSize
    ) {

        Specification<Event> specification = Specification.where(null);
        if (startDate != null) {
            specification = specification.and(EventSpecifications.startDateTimeGreaterThanOrEqual(startDate));
        }

        if (endDate != null) {
            specification = specification.and(EventSpecifications.endDateTimeLessThanOrEqual(endDate));
        }
        if (tags != null && !tags.isEmpty()) {
            List<String> tagsList = QueryParser.parse(tags);
            specification = specification.and(EventSpecifications.hasTags(tagsList));
        }
        if (characteristics != null && !characteristics.isEmpty()) {
            List<String> characteristicsList = QueryParser.parse(characteristics);
            specification = specification.and(EventSpecifications.hasCharacteristics(characteristicsList));
        }
        if (isFree != null) {
            specification = specification.and(EventSpecifications.isFree(isFree));
        }
        if (eventStatus != null && !eventStatus.isEmpty()) {
            List<EventStatus> eventStatusList = QueryParser.parse(eventStatus).stream()
                    .map(EventStatus::valueOf)
                    .toList();

            specification = specification.and(EventSpecifications.hasEventTypes(eventStatusList));
        }
        if (searchInput != null && !searchInput.isEmpty()) {
            specification = specification.and(EventSpecifications.containsText(searchInput));
        }
        if (myEvents != null) {
            // Logic for filtering based on the user's events (admin-specific)
        }

        PageRequest pageRequest = PageRequest.of(offset / pageSize, pageSize);

        Page<Event> eventPage = eventRepository.findAll(specification ,pageRequest);


        return eventPage.getContent()
                .stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public EventDto getEventById(Long id) throws EventNotFoundException {
        return eventRepository.findById(id).map(eventMapper::toDto)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));
    }
}
