package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;
import com.nesslabs.nesslabspring.model.Event;
import com.nesslabs.nesslabspring.repository.EventRepository;
import com.nesslabs.nesslabspring.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventValidator eventValidator;
    private final JwtService jwtService;
    @Override
    public void createEvent(EventDto eventDto, String token) throws InvalidInputException {
        String adminEmail = jwtService.extractUsername(token);
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
