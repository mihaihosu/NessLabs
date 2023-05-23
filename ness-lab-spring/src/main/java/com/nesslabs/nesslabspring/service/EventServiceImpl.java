package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.enums.EventStatus;
import com.nesslabs.nesslabspring.exception.EventNotFoundException;
import com.nesslabs.nesslabspring.mappers.EventMapper;
import com.nesslabs.nesslabspring.model.Event;
import com.nesslabs.nesslabspring.repository.EventRepository;
import com.nesslabs.nesslabspring.security.JwtService;
import com.nesslabs.nesslabspring.utils.QueryParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import com.nesslabs.nesslabspring.exception.InvalidInputException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final JwtService jwtService;
    private final EventValidator eventValidator;
    @Override
    public List<EventDto> getEventsWithPaginationAndFiltered(
            LocalDateTime startDate, LocalDateTime endDate, String tags, String characteristics,
            Boolean isFree, String eventStatus, String searchInput, Boolean myEvents,
            Integer offset, Integer pageSize, String token
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
        if (myEvents != null  && myEvents) {
            String tokenWithoutBearer = token.substring(7);
            if(jwtService.extractIsAdmin(tokenWithoutBearer)) {
                String userEmail = jwtService.extractUsername(tokenWithoutBearer);
                specification = specification.and(EventSpecifications.isMyEvent(userEmail));
            }
        }

        PageRequest pageRequest = PageRequest.of(offset / pageSize, pageSize);

        Page<Event> eventPage = eventRepository.findAll(specification, pageRequest);


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

    public Event updateEvent(Long eventId, EventDto eventDto, String token) throws InvalidInputException {
        // Validate owner
        eventValidator.validateEventOwner(eventId, token);

        // Other validations
        eventValidator.validate(eventDto);
        Event event = new Event();
        setEventFields(event,eventDto);

        LocalDateTime startDateTime = event.getStartDateTime();
        LocalDateTime endDateTime = event.getEndDateTime();
        Duration duration = Duration.between(startDateTime,endDateTime);

        //eventRepository.save(event);
    }

    private void setEventFields(Event event, EventDto eventDto) {
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setStartDateTime(LocalDateTime.of(eventDto.getStartDate(), eventDto.getStartTime()));
        event.setEndDateTime(LocalDateTime.of(eventDto.getEndDate(), eventDto.getStartTime().plus(eventDto.getDuration())));
        event.setAddress(eventDto.getAddress());
        event.setEventLink(event.getEventLink());
        event.setTicketLink(event.getTicketLink());
        event.setPhoto(eventDto.getPhoto());
        event.setFree(eventDto.isFree());
        event.setPetFriendly(eventDto.isPetFriendly());
        event.setKidFriendly(eventDto.isKidFriendly());
        event.setTagName(eventDto.getTagName());
        //event.setEventStatus(eventDto.getEventStatus());
    }
}
