package com.nesslabs.nesslabspring.controller;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventController {

    EventService eventService;


    @GetMapping("/events")
    public ResponseEntity <List<EventDto>> getEventsWithPaginationAndFiltered(
            @RequestParam(required = false, value = "start_date") LocalDate startDate,
            @RequestParam(required = false, value = "end_date") LocalDate endDate,
            @RequestParam(required = false, value = "tags") String tags,
            @RequestParam(required = false, value = "characteristics") String characteristics,
            @RequestParam(required = false, value = "is_free") Integer is_free,
            @RequestParam(required = false, value = "event_types") Integer event_types,
            @RequestParam(required = false, value = "search_input") String search_input,
            @RequestParam(required = false, value = "my_events") Integer my_events,
            @RequestParam(value = "page_number") Integer page_number,
            @RequestParam(value = "page_size") Integer page_size
    ) {

        List<EventDto> filtered_events = eventService.getEventsWithPaginationAndFiltered( startDate, endDate, tags, characteristics, is_free, event_types,
                search_input, my_events, page_number, page_size);
        return new ResponseEntity<>(filtered_events, HttpStatus.OK);
    }
}
