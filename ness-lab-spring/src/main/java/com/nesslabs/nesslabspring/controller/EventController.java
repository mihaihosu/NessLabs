package com.nesslabs.nesslabspring.controller;


import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.InvalidInputException;
import com.nesslabs.nesslabspring.exception.UnauthorizedException;
import com.nesslabs.nesslabspring.model.Event;
import com.nesslabs.nesslabspring.security.JwtService;
import com.nesslabs.nesslabspring.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/events/")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long eventId,
            @RequestBody EventDto eventDto,
            @RequestHeader("Authorization") String token) {
        try {
            Event updatedEvent = eventService.updateEvent(eventId, eventDto, token);
            return ResponseEntity.ok(updatedEvent);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }
    }

}
