package com.nesslabs.nesslabspring.mappers;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.model.Event;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class EventMapper {
    public EventDto toDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .photo(event.getPhoto())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDate(event.getStartDateTime().toLocalDate())
                .endDate(event.getEndDateTime().toLocalDate())
                .startTime(event.getStartDateTime().toLocalTime())
                .duration(Duration.between(event.getEndDateTime().toLocalTime(), (event.getStartDateTime().toLocalTime())))
                .address(event.getAddress())
                .eventLink(event.getEventLink())
                .ticketLink(event.getTicketLink())
                .isFree(event.isFree())
                .isPetFriendly(event.isPetFriendly())
                .isKidFriendly(event.isKidFriendly())
                .adminEmail(event.getAdminEmail())
                .tagName(event.getTagName())
                .eventStatus(event.getEventStatus())
                .build();
    }

    public Event fromDto(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .photo(eventDto.getPhoto())
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .startDateTime(LocalDateTime.of(eventDto.getStartDate(), eventDto.getStartTime()))
                .endDateTime(LocalDateTime.of(eventDto.getEndDate(), eventDto.getStartTime().plus(eventDto.getDuration())))
                .address(eventDto.getAddress())
                .eventLink(eventDto.getEventLink())
                .ticketLink(eventDto.getTicketLink())
                .isFree(eventDto.isFree())
                .isPetFriendly(eventDto.isPetFriendly())
                .isKidFriendly(eventDto.isKidFriendly())
                .adminEmail(eventDto.getAdminEmail())
                .tagName(eventDto.getTagName())
                .eventStatus(eventDto.getEventStatus())
                .build();
    }

}
