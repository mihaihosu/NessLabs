package com.nesslabs.nesslabspring.controller;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.exception.EventNotFoundException;
import com.nesslabs.nesslabspring.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RequestMapping(path = "api/v1")
@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> getEventsWithPaginationAndFiltered(
            @RequestParam(required = false, value = "start_date") LocalDateTime startDate,
            @RequestParam(required = false, value = "end_date") LocalDateTime endDate,
            @RequestParam(required = false, value = "tags") String tags,
            @RequestParam(required = false, value = "characteristics") String characteristics,
            @RequestParam(required = false, value = "is_free") Boolean is_free,
            @RequestParam(required = false, value = "event_status") String event_status,
            @RequestParam(required = false, value = "search_input") String search_input,
            @RequestParam(required = false, value = "my_events") Boolean my_events,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "page_size") Integer page_size,
            @RequestHeader("Authorization") String token
    ) {
        List<EventDto> filtered_events = eventService.getEventsWithPaginationAndFiltered( startDate, endDate, tags, characteristics, is_free, event_status,
                search_input, my_events, offset, page_size, token);
        return new ResponseEntity<>(filtered_events, HttpStatus.OK);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id){
        try {
            EventDto eventDto = eventService.getEventById(id);
            return new ResponseEntity<>(eventDto, HttpStatus.OK);
        }catch (EventNotFoundException e){
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

    }

}
