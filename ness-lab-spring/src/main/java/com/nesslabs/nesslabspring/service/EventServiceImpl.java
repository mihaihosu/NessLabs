package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;
import com.nesslabs.nesslabspring.model.Event;
import com.nesslabs.nesslabspring.repository.EventRepository;
import com.nesslabs.nesslabspring.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    private final EventValidator eventValidator;

    public Event updateEvent(Long eventId, EventDto eventDto, String token) throws InvalidInputException {
        // Validate user authorization
        eventValidator.validateUserAuthorization(eventId, token);

        // Other validations
        eventValidator.validate(eventDto, eventId);

        // Get the existing event from the repository
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + eventId));

        eventFields(existingEvent,eventDto);

        // Save the updated event
        return eventRepository.saveAndFlush(existingEvent);
    }


    private void eventFields(Event event, EventDto eventDto){
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setStartDateTime(LocalDateTime.of(eventDto.getStartDate(), eventDto.getStartTime()));
        event.setEndDateTime(LocalDateTime.of(eventDto.getEndDate(), eventDto.getStartTime().plus(eventDto.getDuration())));
        event.setAddress(eventDto.getAddress());
        event.setEventLink(eventDto.getEventLink());
        event.setTicketLink(eventDto.getTicketLink());
        event.setPhoto(eventDto.getPhoto());
        event.setFree(eventDto.isFree());
        event.setPetFriendly(eventDto.isPetFriendly());
        event.setKidFriendly(eventDto.isKidFriendly());
        event.setTagName(eventDto.getTagName());
        event.setEventStatus(eventDto.getEventStatus());
    }

    public Event createEvent(EventDto eventDto, String adminEmail) {
        // Create a new Event instance and set its properties from the DTO
        Event event = new Event();
        eventFields(event, eventDto);
        event.setAdminEmail(adminEmail); // Set the admin email

        // Set the duration based on the start and end date times
        LocalDateTime startDateTime = event.getStartDateTime();
        LocalDateTime endDateTime = event.getEndDateTime();
        Duration duration = Duration.between(startDateTime, endDateTime);

        // Save the event and return the created event
        return eventRepository.save(event);
    }












}
