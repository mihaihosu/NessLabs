package com.nesslabs.nesslabspring.controller;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.model.Event;
import com.nesslabs.nesslabspring.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

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
