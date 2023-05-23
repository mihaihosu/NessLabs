package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;
import com.nesslabs.nesslabspring.exception.UnauthorizedException;
import com.nesslabs.nesslabspring.model.Event;
import com.nesslabs.nesslabspring.repository.EventRepository;
import com.nesslabs.nesslabspring.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventValidatorImpl implements EventValidator{

    private final JwtService jwtService;
    private final EventRepository eventRepository;

    private static final String URL_PATTERN = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$";

    @Override
    public void validate(EventDto eventDto) throws InvalidInputException {
        validateRequiredFields(eventDto);
        validateDates(eventDto);
        validateLinks(eventDto);
        validateDuration(eventDto);
    }

    private void validateRequiredFields(EventDto eventDto) throws InvalidInputException {
        if (eventDto.getPhoto().isEmpty()) {
            throw new InvalidInputException("Photo is required.");
        }
        if (eventDto.getTitle().isEmpty()) {
            throw new InvalidInputException("Title field is required.");
        }
        if (eventDto.getDescription().isEmpty()) {
            throw new InvalidInputException("Description field is required.");
        }
        if (eventDto.getStartDate() == null || eventDto.getEndDate() == null || eventDto.getStartTime() == null) {
            throw new InvalidInputException("Start date, end date, and time fields are required.");
        }
    }

    private void validateDates(EventDto eventDto) throws InvalidInputException {
        if (eventDto.getStartDate().isAfter(eventDto.getEndDate())) {
            throw new InvalidInputException("End date cannot be before start date.");
        }
    }

    private void validateLinks(EventDto eventDto) throws InvalidInputException {
        if (!eventDto.getEventLink().matches(URL_PATTERN) && !eventDto.getEventLink().isEmpty()) {
            throw new InvalidInputException("Event link is not a valid URL.");
        }
        if (!eventDto.getTicketLink().matches(URL_PATTERN) && !eventDto.getTicketLink().isEmpty()) {
            throw new InvalidInputException("Ticket link is not a valid URL.");
        }
    }


    private void validateDuration(EventDto eventDto) throws InvalidInputException {


    }

    private void validateUserAuthorization(Long eventId, String token) throws UnauthorizedException{
        Long userId = jwtService.getUserIdFromToken(token);
        Event event = eventRepository.findEventById(eventId);
        if (!event.getAdminId().equals(userId)) {
            throw new UnauthorizedException("User is not authorized to edit this event.");
        }
    }

}
