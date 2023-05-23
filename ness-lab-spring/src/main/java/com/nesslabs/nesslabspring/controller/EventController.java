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
import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.model.Event;
import com.nesslabs.nesslabspring.service.EventService;
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
    public ResponseEntity<?> updateEvent(
            @PathVariable Long eventId,
            @RequestBody EventDto eventDto,
            @RequestHeader("Authorization") String token) {
        try {
            Event updatedEvent = eventService.updateEvent(eventId, eventDto, token);
            return ResponseEntity.status(200).body(updatedEvent); // Returns HTTP status 200 (OK)
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(401).body(null); // Returns HTTP status 401 (Unauthorized)
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).build(); // Returns HTTP status 404 (Not Found)
        } catch (InvalidInputException e) {
            return ResponseEntity.status(500).body(e.getMessage()); // Returns HTTP status 500 (Internal Server Error)
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody EventDto eventDto, @RequestHeader("Authorization") String token) {
        try{
            eventService.createEvent(eventDto, token);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
