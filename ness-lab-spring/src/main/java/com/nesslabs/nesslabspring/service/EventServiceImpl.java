package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;
import com.nesslabs.nesslabspring.exception.JwtAuthenticationException;
import com.nesslabs.nesslabspring.exception.UnauthorizedException;
import com.nesslabs.nesslabspring.model.Event;
import com.nesslabs.nesslabspring.repository.EventRepository;
import com.nesslabs.nesslabspring.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventValidator eventValidator;
    private final JwtService jwtService;
    private final EventRepository eventRepository;

    @Override
    public Event updateEvent(Long eventId, EventDto eventDto, String token) throws InvalidInputException {
        return null;
    }

    @Override
    public void createEvent(EventDto eventDto, String token) throws InvalidInputException, UnauthorizedException{

        String adminEmail = jwtService.extractUsername(token);
        System.out.println(eventDto);
        if(jwtService.extractIsAdmin(token)) {
            try{
                jwtService.getAuthentication(token);
                eventValidator.validate(eventDto);
            }catch (InvalidInputException e) {
                throw new InvalidInputException("Invalid input");
            }catch (JwtAuthenticationException e) {
                throw new JwtAuthenticationException("Invalid token");
            }
            Event event = new Event();
            setEventFields(event,eventDto);
            event.setAdminEmail(adminEmail);
            eventRepository.save(event);
        } else throw new UnauthorizedException("Unauthorized");
    }

    private void setEventFields(Event event, EventDto eventDto) {
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
}
